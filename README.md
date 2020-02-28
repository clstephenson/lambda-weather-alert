# lambda-weather-alert
AWS Lambda function that uses the weather.gov API and send me daily weather information in an SMS message.

## Purpose
I created this small project as a learning exercise for myself, and it had three main goals.
1. Expand my knowledge of AWS services. 
   - AWS Lambda
   - Simple Notification Service (SNS)
2. Write a Java program that consumes an external API. 
   - weather.gov
3. Use Travis CI to automatically build and deploy to AWS when changes are pushed to my GitHub repo.

## Description
This little application is deployed to an AWS Lambda function. It runs each morning and reaches out to the weather.gov public API to get today's high temperature and tonights low. It then publishes a message to a specific topic on AWS SNS. That topic is configured to send a text message to subscribers (i.e. me).

![System Architecture Diagram](https://clstephenson.s3-us-west-2.amazonaws.com/apps/lambda-weather-alert/lamda-weather-alert-diagram.png "System Architecture Diagram")
