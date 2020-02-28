package com.clstephenson.lambdaweatheralert;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SnsMessagePublisher {

    private final String topicArn;

    public static SnsMessagePublisher getPublisher(String topicArn) {
        return new SnsMessagePublisher(topicArn);
    }

    private SnsMessagePublisher(String topicArn) {
        this.topicArn = topicArn;
    }

    public String publish(String message) throws SnsMessagePublisherException {
        AmazonSNS SNSClient = AmazonSNSClientBuilder.defaultClient();
        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(this.topicArn)
                .withMessage(message);
        PublishResult publishResult = null;
        try {
            publishResult = SNSClient.publish(publishRequest);
        } catch (Exception e) {
            throw new SnsMessagePublisherException(e);
        }
        return publishResult.getMessageId();
    }

}
