package com.clstephenson.lambdaweatheralert;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSourceFactory;

public class LambdaWeatherAlert implements RequestHandler<ScheduledEvent, Void> {

    // environment variable names configured on AWS Lambda function
    private static final String ENV_VAR_LOW_TEMP_THRESHOLD = "LOW_TEMP_THRESHOLD";
    private static final String ENV_VAR_LATITUDE = "LATITUDE"; //environment variable specifying latitude
    private static final String ENV_VAR_LONGITUDE = "LONGITUDE"; //environment variable specifying longitude
    private static final String ENV_VAR_SNS_TOPIC_ARN = "SNS_TOPIC";

    // Strings used in messages
    private static final String MESSAGE_TEXT = "It will be %d degrees tonight!";
    private static final String NO_WARNINGS_TEXT = "No weather warnings to publish.";

    private static Location location;

    static {
        location = Location.getLocationFromCoordinates(
                System.getenv(ENV_VAR_LATITUDE),
                System.getenv(ENV_VAR_LONGITUDE)
        );
    }

    /**
     * Main entrypoint when running on local system or from within an IDE; this will not be used when running
     * from AWS Lambda;
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            int lowTemp = WeatherSourceFactory
                    .getWeatherSourceAtLocation(location)
                    .getLowTemperatureForTonight();
            if (lowTemp < Integer.parseInt(System.getenv(ENV_VAR_LOW_TEMP_THRESHOLD))) {
                System.out.println(String.format(MESSAGE_TEXT, lowTemp));
            } else {
                System.out.println(NO_WARNINGS_TEXT);
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
            int lowTemp = WeatherSourceFactory
                    .getWeatherSourceAtLocation(location)
                    .getLowTemperatureForTonight();
            if (lowTemp < Integer.parseInt(System.getenv(ENV_VAR_LOW_TEMP_THRESHOLD))) {
                String SNSTopicARN = System.getenv(ENV_VAR_SNS_TOPIC_ARN);
                logger.log(String.format("Publishing message to SNS topic: %s", SNSTopicARN));
                String messageId = publishToSNSTopic(SNSTopicARN, String.format(MESSAGE_TEXT, lowTemp));
                logger.log(String.format("SNS message ID: %s", messageId));
            } else {
                logger.log(NO_WARNINGS_TEXT);
            }
        } catch (Exception e) {
            logger.log("LambdaWeatherAlert request failed.");
            logger.log("Function name: " + context.getFunctionName());
            logger.log("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
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
