package com.hackatonalcoolico.coingame.config.auth.configs;

import com.hackatonalcoolico.coingame.config.auth.entities.MyUserDetails;
import com.hackatonalcoolico.coingame.config.auth.service.AuthServices;
import com.hackatonalcoolico.coingame.config.auth.utils.AuthTools;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private AuthServices authenticationServices;

    public CustomAuthenticationManager() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        Player player = authenticationServices.findByUsername(username);

        if (player == null) {
            throw new BadCredentialsException("Player not existent!");
        }

        if (player.isBlocked()) {
            throw new DisabledException("You have been blocked by the admin of the app!");
        }

        if (!player.isEnabled()) {
            throw new DisabledException("Waiting for board acceptance of the request!");
        }

        if (AuthTools.checkPassword(password, player.getPassword())) {
            throw new DisabledException("Incorrect password!");
        }

        MyUserDetails userDetails = new MyUserDetails(player);
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

}
