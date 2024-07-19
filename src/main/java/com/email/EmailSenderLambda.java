package com.email;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

public class EmailSenderLambda implements RequestHandler<EmailRequest, String> {

    private final SesClient sesClient = SesClient.builder().build();
    private final String SENDER_EMAIL = "youremail@email"; // Replace with your verified sender email

    @Override
    public String handleRequest(EmailRequest emailRequest, Context context) {
        try {
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .source(SENDER_EMAIL)
                    .destination(Destination.builder()
                            .toAddresses(emailRequest.getRecipient())
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder().data(emailRequest.getSubject()).build())
                            .body(Body.builder()
                                    .text(Content.builder().data(emailRequest.getBody()).build())
                                    .build())
                            .build())
                    .build();

            sesClient.sendEmail(sendEmailRequest);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }
}
