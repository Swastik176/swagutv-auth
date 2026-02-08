package com.swagutv.auth.repo;


import com.swagutv.auth.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepo extends JpaRepository<Report, UUID> {
}

