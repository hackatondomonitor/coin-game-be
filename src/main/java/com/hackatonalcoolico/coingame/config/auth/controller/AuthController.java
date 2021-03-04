package com.hackatonalcoolico.coingame.config.auth.controller;

import com.hackatonalcoolico.coingame.config.auth.dto.ResetPasswordDTO;
import com.hackatonalcoolico.coingame.config.auth.service.AuthServices;
import com.hackatonalcoolico.coingame.config.auth.utils.AuthTools;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthServices authServices;

    public AuthController() {
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasPermission('AuthController', 'deletePlayer')")
    public @ResponseBody ResponseEntity<String> deletePlayer(@PathVariable Long userId) {
        authServices.removeUser(userId);
        return ResponseEntity.ok("Player deleted!");
    }

    @PutMapping("/change-password/{userId}")
    public @ResponseBody ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody ResetPasswordDTO resetPasswordDTO) {
        if(resetPasswordDTO == null || resetPasswordDTO.getCurrentPassword() == null || resetPasswordDTO.getNewPassword() == null)
            return ResponseEntity.status(400).body("Invalid data!");

        Player player = authServices.findById(userId);

        if(player != null) {
            if (AuthTools.checkPassword(resetPasswordDTO.getCurrentPassword(), player.getPassword())) {
                return ResponseEntity.status(400).body("Current password sent is not equal to user password!");
            }
        } else {
            return ResponseEntity.status(404).body("Player not found!");
        }

        authServices.resetPassword(userId, resetPasswordDTO.getNewPassword());

        return ResponseEntity.ok("Password is changed!");
    }

    @PutMapping("/block/{userId}")
    @PreAuthorize("hasPermission('AuthController', 'blockUser')")
    public ResponseEntity<String> blockUser(@PathVariable Long playerId) {
        Player player = authServices.findById(playerId);

        if(player == null)
            return ResponseEntity.status(404).body("Player not found!");

        authServices.blockUser(playerId);

        return ResponseEntity.ok("User blocked!");
    }

    @PutMapping("/unblock/{userId}")
    @PreAuthorize("hasPermission('AuthController', 'unblockUser')")
    public ResponseEntity<String> unblockUser(@PathVariable Long playerId) {
        Player player = authServices.findById(playerId);

        if(player == null)
            return ResponseEntity.status(404).body("Player not found!");

        authServices.unblockUser(playerId);

        return ResponseEntity.ok("User unblocked!");
    }
}
