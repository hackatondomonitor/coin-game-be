package com.hackatonalcoolico.coingame.config.auth.entities;

import com.hackatonalcoolico.coingame.core.models.entities.Player;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private Player player;

    public MyUserDetails(Player player){
        this.player = player;
    }

    public boolean isAdministrator(){
        Role role = player.getRole();

        return role != null && ((role.equals(Role.ADMIN)));
    }

    public Long getPlayerId(){
        return this.player.getPlayerId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = player.getRole();
        if(role != null) {
            authorities.add(new SimpleGrantedAuthority( role.toString()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return player.getPassword();
    }

    @Override
    public String getUsername() {
        return player.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
