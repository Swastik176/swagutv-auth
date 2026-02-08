package com.swagutv.auth.service;

import com.swagutv.auth.entity.Block;
import com.swagutv.auth.entity.User;
import com.swagutv.auth.repo.BlockRepo;
import com.swagutv.auth.repo.UserRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlockService {

    @Autowired
    BlockRepo blockRepo;

    @Autowired
    UserRepo userRepo;

    public void blockUser(UUID userId, String blockerEmail){
        User blocker = userRepo.findByEmail(blockerEmail).orElseThrow();
        User blocked = userRepo.findById(userId).orElseThrow();


        if (blockRepo.existsByBlockerAndBlocked(
                blocker, blocked
        )) {
            return;
        }

        Block block = new Block();
        block.setBlocker(blocker);
        block.setBlocked(blocked);
        blockRepo.save(block);
    }

    public List<User> getBlockedUsers(String email){
        User blocker = userRepo.findByEmail(email).orElseThrow();

        return blockRepo.findByBlocker(blocker)
                .stream()
                .map(Block::getBlocked)
                .toList();
    }

    public boolean canChat(String emailA, String emailB) {

        System.out.println("canChat: " + emailA + " <-> " + emailB);

        User userA = userRepo.findByEmail(emailA).orElse(null);
        User userB = userRepo.findByEmail(emailB).orElse(null);

        if (userA == null || userB == null) {
            // if we cannot resolve users, DO NOT match
            return false;
        }

        boolean aBlockedB = blockRepo.existsByBlockerAndBlocked(userA, userB);
        boolean bBlockedA = blockRepo.existsByBlockerAndBlocked(userB, userA);

        return !(aBlockedB || bBlockedA);
    }


}
