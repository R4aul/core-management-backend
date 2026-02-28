package com.business_control_system.domain.dto;

import com.business_control_system.perisistence.entity.UserEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthUser implements UserDetails {

    private UserEntity user;

    public AuthUser(UserEntity user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() != null){
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            if (user.getRole().getPermissions() != null){
                user.getRole().getPermissions().forEach(permissionEntity -> {
                    authorities.add(new SimpleGrantedAuthority(permissionEntity.getName()));
                });
            }
        }
        else {
            return Collections.emptyList();
        }
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
