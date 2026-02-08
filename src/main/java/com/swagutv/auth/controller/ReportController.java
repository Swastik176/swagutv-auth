package com.swagutv.auth.controller;

import com.swagutv.auth.service.ReportService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/{userId}")
    public String reportUser(@PathVariable UUID userId, @RequestParam String reason, Authentication auth){
        reportService.reportUser(auth.name(), userId, reason);
        return "User Reported";
    }
}
