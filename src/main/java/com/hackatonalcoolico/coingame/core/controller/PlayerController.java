package com.hackatonalcoolico.coingame.core.controller;

import com.hackatonalcoolico.coingame.config.auth.dto.CreateUserDTO;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import com.hackatonalcoolico.coingame.core.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody CreateUserDTO createUserDTO) {
        Player player = playerService.createPlayer(createUserDTO);

        if(player != null) {
            return ResponseEntity.ok(player);
        } else return ResponseEntity.badRequest().body(null);
    }
}
