package com.swagutv.auth.service;

import com.swagutv.auth.exception.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {

        try{
            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setTo(to);
            message.setSubject("SwaGUTv OTP Verification");
            message.setText(
                    "Welcome to SwaGUTv," +
                            "\nYour OTP for verification is: " + otp +
                            "\nValid for 5 minutes."
            );
            message.setFrom("swastikg177@gmail.com");
            mailSender.send(message);
        }
        catch(MailException e){
            System.err.println("Email sending failed: " + e.getMessage());
            e.printStackTrace();

            throw new EmailNotSentException("Failed to send otp mail");
        }
    }
}
