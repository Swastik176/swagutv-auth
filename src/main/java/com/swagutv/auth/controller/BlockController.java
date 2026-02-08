package com.swagutv.auth.controller;

import com.swagutv.auth.dto.CanChatRequest;
import com.swagutv.auth.service.BlockService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    BlockService blockService;

    @PostMapping("/{userId}")
    public String blockUser(@PathVariable UUID userId, Authentication auth){
        blockService.blockUser(userId, auth.name());
        return "User Blocked";
    }

    @PostMapping("/can-chat")
    public Boolean canChat(@RequestBody CanChatRequest req) {
        return blockService.canChat(
                req.getUserA(),
                req.getUserB()
        );
    }

}
