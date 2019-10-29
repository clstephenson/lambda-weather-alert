package com.clstephenson.lambdaweatheralert;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.clstephenson.lambdaweatheralert.nwsapi.forecast.Forecast;
import com.clstephenson.lambdaweatheralert.nwsapi.forecast.Period;
import com.clstephenson.lambdaweatheralert.nwsapi.point.Point;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class LambdaWeatherAlert implements RequestHandler<ScheduledEvent, Void> {

    private static final String API_TARGET_URI = "https://api.weather.gov";
    private static final String API_PATH = "points";

    // environment variable names configured on AWS Lambda function
    private static final String ENV_VAR_LOW_TEMP_THRESHOLD = "LOW_TEMP_THRESHOLD";
    private static final String ENV_VAR_LATITUDE = "LATITUDE"; //environment variable specifying latitude
    private static final String ENV_VAR_LONGITUDE = "LONGITUDE"; //environment variable specifying longitude
    private static final String ENV_VAR_SNS_TOPIC_ARN = "SNS_TOPIC";

    private static final String MESSAGE_TEXT = "It will be %d degrees tonight!";

    private static Client restClient;

    /**
     * Main entrypoint when running on local system or from within an IDE; this will not be used when running
     * from AWS Lambda;
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            int lowTemp = getTonightsLowTemperature();
            if (lowTemp < Integer.parseInt(System.getenv(ENV_VAR_LOW_TEMP_THRESHOLD))) {
                System.out.println(String.format(MESSAGE_TEXT, lowTemp));
            } else {
                System.out.println("No weather warnings to publish.");
            }
        } catch (Exception e) {
            System.err.println("LambdaWeatherAlert request failed.");
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main entrypoint to be used by AWS Lambda; The function will be configured to run on a schedule using an AWS
     * Cloudwatch scheduled event.
     *
     * @param event   ScheduledEvent object passed in automatically when the function is called.
     * @param context Contains information within the Lambda environment.
     */
    public Void handleRequest(ScheduledEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        try {
            int lowTemp = getTonightsLowTemperature();
            if (lowTemp < Integer.parseInt(System.getenv(ENV_VAR_LOW_TEMP_THRESHOLD))) {
                String SNSTopicARN = System.getenv(ENV_VAR_SNS_TOPIC_ARN);
                logger.log(String.format("Publishing message to SNS topic: %s", SNSTopicARN));
                String messageId = publishToSNSTopic(SNSTopicARN, String.format(MESSAGE_TEXT, lowTemp));
                logger.log(String.format("SNS message ID: %s", messageId));
            } else {
                logger.log("No weather warnings to publish.");
            }
        } catch (Exception e) {
            logger.log("LambdaWeatherAlert request failed.");
            logger.log("Function name: " + context.getFunctionName());
            logger.log("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static int getTonightsLowTemperature() {
        restClient = ClientBuilder.newClient();
        Point point = getPointDataFromAPI();
        Forecast forecast = getForecastDataFromAPI(point.getProperties().getForecast());
        List<Period> periodList = forecast.getProperties().getPeriods();
        Period period = periodList
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase("tonight"))
                .findFirst()
                .get();
        return period.getTemperature();
    }

    private static Point getPointDataFromAPI() {
        String pointLatitude = System.getenv(ENV_VAR_LATITUDE);
        String pointLongitude = System.getenv(ENV_VAR_LONGITUDE);
        return restClient
                .target(API_TARGET_URI)
                .path(String.format("%s/%s,%s", API_PATH, pointLatitude, pointLongitude))
                .request(MediaType.APPLICATION_JSON)
                .get(Point.class);
    }

    private static Forecast getForecastDataFromAPI(String uri) {
        return restClient
                .target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
    }

    private String publishToSNSTopic(String topicARN, String message) {
        AmazonSNS SNSClient = AmazonSNSClientBuilder.defaultClient();
        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(topicARN)
                .withMessage(message);
        PublishResult publishResult = SNSClient.publish(publishRequest);
        return publishResult.getMessageId();
    }
}
