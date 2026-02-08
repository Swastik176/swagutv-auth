package com.swagutv.auth.security;

import com.swagutv.auth.entity.Role;
import com.swagutv.auth.entity.User;
import com.swagutv.auth.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void seedAdmin() {

        String adminEmail = "swastikg177@gmail.com";

        if (userRepo.findByEmail(adminEmail).isPresent())
            return;

        User admin = new User();
        admin.setName("Swastik Admin");
        admin.setEmail(adminEmail);
        admin.setPassword(
                encoder.encode("swagutv.in@galgotiasuniversity")
        );
        admin.setRole(Role.ADMIN);
        admin.setVerified(true);

        userRepo.save(admin);

        System.out.println("Admin account created");
    }
}
