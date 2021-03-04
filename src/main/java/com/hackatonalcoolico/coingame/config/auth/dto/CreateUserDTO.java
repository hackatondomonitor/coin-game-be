package com.hackatonalcoolico.coingame.config.auth.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserDTO {
    @NotNull
    private String password;
    @NotNull
    private String username;
}
