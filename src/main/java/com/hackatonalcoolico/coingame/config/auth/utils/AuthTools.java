package com.hackatonalcoolico.coingame.config.auth.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthTools {

    public static String getUserName(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return (String) authentication.getPrincipal();
        } else {
            return "Anonymous";
        }
    }

    public static String encodePassword(String text) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(text);
    }

    public static boolean checkPassword(String cleanPass, String passBD) {
        return !BCrypt.checkpw(cleanPass, passBD);
    }
}
