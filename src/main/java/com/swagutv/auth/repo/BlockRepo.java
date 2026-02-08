package com.swagutv.auth.repo;


import com.swagutv.auth.entity.Block;
import com.swagutv.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface BlockRepo extends JpaRepository<Block, UUID> {
    boolean existsByBlockerAndBlocked(User blocker, User blocked);

    List<Block> findByBlocker(User blocker);
}

