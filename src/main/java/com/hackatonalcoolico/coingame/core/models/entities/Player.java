package com.hackatonalcoolico.coingame.core.models.entities;

import com.hackatonalcoolico.coingame.config.auth.entities.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Player  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isBlocked;
    private boolean isEnabled;
}
