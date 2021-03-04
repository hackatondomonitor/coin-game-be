package com.hackatonalcoolico.coingame.core.models.dto;

import com.hackatonalcoolico.coingame.config.auth.entities.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
public class PlayerDTO {
    private Long playerId;
    private String username;
    private String role;
    private boolean isBlocked;
}
