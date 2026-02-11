package com.swagutv.auth.controller;

import com.swagutv.auth.dto.LoginRequest;
import com.swagutv.auth.entity.Role;
import com.swagutv.auth.entity.User;
import com.swagutv.auth.exception.EmailNotVerifiedException;
import com.swagutv.auth.exception.InvalidCredentialsException;
import com.swagutv.auth.exception.UserAlreadyExistException;
import com.swagutv.auth.repo.UserRepo;
import com.swagutv.auth.service.OtpService;
import com.swagutv.auth.service.AuthService;
import com.swagutv.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    OtpService otpService;

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (!user.getEmail()
                .endsWith("@galgotiasuniversity.edu.in")) {

            throw new RuntimeException(
                    "Only Galgotias University emails allowed"
            );
        }

        Optional<User> existing =
                userRepo.findByEmail(user.getEmail());

        if (existing.isPresent()) {

            User dbUser = existing.get();

            if (dbUser.isVerified()) {
                throw new UserAlreadyExistException(
                        "Email already registered"
                );
            }

            // user exists but not verified → resend OTP
            otpService.generateAndSendOtp(dbUser.getEmail());

            return "OTP resent to email";
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        user.setRole(Role.USER);
        user.setVerified(false);
        userRepo.save(user);

        otpService.generateAndSendOtp(user.getEmail());
        userRepo.save(user);

        return "OTP sent to email";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        User user = userRepo
                .findByEmail(req.getEmail())
                .orElseThrow();

        if (!user.isVerified()) {
            throw new EmailNotVerifiedException(
                    "Email not verified!!"
            );
        }

        if (!encoder.matches(
                req.getPassword(),
                user.getPassword()
        )) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );
    }

    @GetMapping("/profile")
    public String profile(){
        return "Profile ok";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {

        boolean valid =
                otpService.verifyOtp(email, otp);

        if (!valid)
            return "Invalid or expired OTP";

        User user =
                userRepo.findByEmail(email)
                        .orElseThrow();

        user.setVerified(true);
        userRepo.save(user);

        return "Email verified";
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        authService.resendOtp(email);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/validate-token")
    public Boolean validateToken(@RequestHeader("Authorization") String token) {
        try {
            jwtUtil.extractEmail(token.replace("Bearer ", ""));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

