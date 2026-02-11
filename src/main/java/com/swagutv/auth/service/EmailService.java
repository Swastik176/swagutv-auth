package com.swagutv.auth.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.swagutv.auth.exception.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendOtpEmail(String to, String otp) {
//
//
//        try{
//            SimpleMailMessage message =
//                    new SimpleMailMessage();
//
//            message.setTo(to);
//            message.setSubject("SwaGUTv OTP Verification");
//            message.setText(
//                    "Welcome to SwaGUTv," +
//                            "\nYour OTP for verification is: " + otp +
//                            "\nValid for 5 minutes."
//            );
//            message.setFrom("swastikg177@gmail.com");
//            mailSender.send(message);
//        }
//        catch(MailException e){
//            System.err.println("Email sending failed: " + e.getMessage());
//            e.printStackTrace();
//
//            throw new EmailNotSentException("Failed to send otp mail");
//        }
//    }

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${app.mail.from}")
    private String fromEmail;

    public void sendOtpEmail(String to, String otp) {

        Email from = new Email(fromEmail);
        Email recipient = new Email(to);

        String subject = "SwaGUTv OTP Verification";

        Content content = new Content("text/html",
                "<h2>Welcome to SwaGUTv</h2>" +
                        "<p>Your OTP is: <b>" + otp + "</b></p>" +
                        "<p>Valid for 5 minutes.</p>"
        );

        Mail mail = new Mail(from, subject, recipient, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() >= 400) {
                throw new RuntimeException("SendGrid Error: " + response.getBody());
            }

        } catch (MailException ex) {
            System.err.println("OTP Email not Sent: " + ex.getMessage());
            ex.printStackTrace();
            throw new EmailNotSentException("Failed to send email");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
