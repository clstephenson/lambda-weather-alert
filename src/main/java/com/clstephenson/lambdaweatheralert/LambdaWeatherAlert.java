package com.clstephenson.lambdaweatheralert;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSource;
import com.clstephenson.lambdaweatheralert.weatherapi.WeatherSourceFactory;

public class LambdaWeatherAlert implements RequestHandler<ScheduledEvent, Void> {

    private static final String MESSAGE_TEXT = "It will be %d degrees today with a low tonight of %d.";
    private static final String ERROR_TEXT = "LambdaWeatherAlert request failed:\n%s\n";

    private static final String SnsTopicArn;
    private static final String latitude;
    private static final String longitude;
    private static Location location;
    private static WeatherSource weatherSource;

    static {
        SnsTopicArn = System.getenv("SNS_TOPIC");
        latitude = System.getenv("LATITUDE");
        longitude = System.getenv("LONGITUDE");

        location = Location.getLocationFromCoordinates(latitude, longitude);

        weatherSource = WeatherSourceFactory.getWeatherSourceAtLocation(location);
    }

    /**
     * Main entrypoint when running on local system or from within an IDE; this will not be used when running
     * from AWS Lambda;
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            int lowTemp = weatherSource.getLowTemperatureForTonight();
            int highTemp = weatherSource.getHighTempForToday();
            System.out.println(String.format(MESSAGE_TEXT, highTemp, lowTemp));
        } catch (Exception e) {
            System.err.printf(ERROR_TEXT, e.getMessage());
            System.out.println();
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
        SnsMessagePublisher messagePublisher = SnsMessagePublisher.getPublisher(SnsTopicArn);

        try {
            int lowTemp = weatherSource.getLowTemperatureForTonight();
            int highTemp = weatherSource.getHighTempForToday();

            logger.log(String.format("Publishing message to SNS topic: %s", SnsTopicArn));
            String messageId = messagePublisher.publish(String.format(MESSAGE_TEXT, highTemp, lowTemp));
            logger.log(String.format("SNS message ID: %s", messageId));

        } catch (SnsMessagePublisherException e) {
            logger.log("LambdaWeatherAlert request failed.");
            logger.log("Function name: " + context.getFunctionName());
            logger.log("Message: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            messagePublisher.publish(String.format(ERROR_TEXT, e.getMessage()));
            logger.log("LambdaWeatherAlert request failed.");
            logger.log("Function name: " + context.getFunctionName());
            logger.log("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
