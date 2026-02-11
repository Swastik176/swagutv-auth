package com.swagutv.auth.service;

import com.swagutv.auth.entity.User;
import com.swagutv.auth.repo.OtpRepo;
import com.swagutv.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    OtpService otpService;

    @Autowired
    OtpRepo otpRepo;

    public void resendOtp(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.isVerified()) {
            throw new IllegalArgumentException("User already verified");
        }

        otpService.generateAndSendOtp(email);
    }
}
