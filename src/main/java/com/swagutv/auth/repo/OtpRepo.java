package com.swagutv.auth.repo;

import com.swagutv.auth.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepo
        extends JpaRepository<OtpVerification, UUID> {

    Optional<OtpVerification> findByEmail(String email);

    void deleteByEmail(String email);
}

