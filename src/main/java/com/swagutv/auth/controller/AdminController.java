package com.swagutv.auth.controller;

import com.swagutv.auth.entity.Report;
import com.swagutv.auth.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ReportRepo reportRepo;

    @GetMapping("/test")
    public String adminTest() {
        return "ADMIN OK";
    }

    @GetMapping("/reports")
    public List<Report> allReports(){
        return reportRepo.findAll();
    }
}

