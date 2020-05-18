package com.example.demo.Dtos;

import java.util.Collection;

import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {
    private Entrant user;

    public CustomUserDetails(Entrant user) {
        this.user = user;
    }

    public CustomUserDetails(Admin user) {
        this.user = new Entrant(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = user.getRole();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userRole);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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