package com.hackatonalcoolico.coingame.config.auth.service;

import com.hackatonalcoolico.coingame.config.auth.entities.MyUserDetails;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthServices authServices;

    @Value("${security.jwt.client-id}")
    private String clientId;

    public UserDetailsServiceImpl(AuthServices authServices) {
        this.authServices = authServices;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(clientId.equals(s)){
            return null;
        }

        Player player = authServices.findByUsername(s);

        if(player == null){
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        return new MyUserDetails(player);
    }
}
