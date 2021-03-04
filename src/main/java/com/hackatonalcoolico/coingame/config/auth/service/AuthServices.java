package com.hackatonalcoolico.coingame.config.auth.service;

import com.hackatonalcoolico.coingame.config.auth.utils.AuthTools;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import com.hackatonalcoolico.coingame.core.repository.PlayerMapping;
import com.hackatonalcoolico.coingame.core.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServices {

    private final PlayerRepository playerRepository;
    private final PlayerMapping playerMapping;

    public AuthServices(PlayerRepository playerRepository, PlayerMapping playerMapping) {
        this.playerRepository = playerRepository;
        this.playerMapping = playerMapping;
    }

    public Player findById(Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);

        return player.orElse(null);
    }

    public void resetPassword(Long userId, String newPassword) {
        Optional<Player> player = playerRepository.findById(userId);

        if(player.isPresent()) {
            String pass = AuthTools.encodePassword(newPassword);
            player.get().setPassword(pass);
            playerRepository.save(player.get());
        }
    }

    public void removeUser(Long userId) {
        playerRepository.deleteById(userId);
    }

    public void blockUser(Long userId) {
        Optional<Player> player = playerRepository.findById(userId);

        if (player.isPresent()) {
            player.get().setBlocked(true);
            playerRepository.save(player.get());
        }
    }

    public void unblockUser(Long userId) {
        Optional<Player> player = playerRepository.findById(userId);

        if (player.isPresent()) {
            player.get().setBlocked(false);
            playerRepository.save(player.get());
        }
    }

    public Player findByUsername(String username) {
        Optional<Player> player = playerRepository.findByUsername(username);
        return player.orElse(null);
    }
}
