package com.hackatonalcoolico.coingame.core.service;

import com.hackatonalcoolico.coingame.config.auth.dto.CreateUserDTO;
import com.hackatonalcoolico.coingame.config.auth.entities.Role;
import com.hackatonalcoolico.coingame.config.auth.utils.AuthTools;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import com.hackatonalcoolico.coingame.core.repository.PlayerMapping;
import com.hackatonalcoolico.coingame.core.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(CreateUserDTO createUserDTO) {
        Optional<Player> checkPlayer = playerRepository.findByUsername(createUserDTO.getUsername());

        if(checkPlayer.isPresent()) {
            return null;
        } else {
            Player player = new Player();

            player.setUsername(createUserDTO.getUsername());
            player.setPassword(AuthTools.encodePassword(createUserDTO.getPassword()));
            player.setBlocked(false);
            player.setEnabled(true);
            player.setRole(Role.PLAYER);

            return playerRepository.save(player);
        }
    }
}
