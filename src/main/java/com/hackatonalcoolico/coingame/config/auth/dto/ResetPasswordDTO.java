package com.hackatonalcoolico.coingame.config.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResetPasswordDTO {
    private String currentPassword;
    private String newPassword;
}
