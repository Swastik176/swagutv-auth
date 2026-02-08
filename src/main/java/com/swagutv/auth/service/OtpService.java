package com.swagutv.auth.service;

import com.swagutv.auth.entity.OtpVerification;
import com.swagutv.auth.repo.OtpRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    OtpRepo otpRepo;

    @Autowired
    EmailService emailService;

    @Transactional
    public void generateAndSendOtp(String email) {

        String otp =
                String.valueOf(
                        new Random()
                                .nextInt(900000) + 100000
                );

        OtpVerification otpEntity =
                new OtpVerification();

        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(
                LocalDateTime.now().plusMinutes(5)
        );

        otpRepo.deleteByEmail(email);
        otpRepo.save(otpEntity);

        emailService.sendOtpEmail(email, otp);
    }

    @Transactional
    public boolean verifyOtp(String email, String otp) {

        OtpVerification record =
                otpRepo.findByEmail(email)
                        .orElseThrow();

        if (LocalDateTime.now()
                .isAfter(record.getExpiryTime())) {
            return false;
        }

        if (!record.getOtp().equals(otp)) {
            return false;
        }

        otpRepo.deleteByEmail(email);
        return true;
    }
}

