package com.swagutv.auth.service;

import com.swagutv.auth.entity.Report;
import com.swagutv.auth.entity.User;
import com.swagutv.auth.repo.ReportRepo;
import com.swagutv.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    ReportRepo reportRepo;

    @Autowired
    UserRepo userRepo;

    public void reportUser(String reporterEmail, UUID reportedUserId, String reason){
        User reporter = userRepo.findByEmail(reporterEmail).orElseThrow();
        User reported = userRepo.findById(reportedUserId).orElseThrow();

        Report report = new Report();
        report.setReporter(reporter);
        report.setReported(reported);
        report.setReason(reason);

        reportRepo.save(report);
    }
}
