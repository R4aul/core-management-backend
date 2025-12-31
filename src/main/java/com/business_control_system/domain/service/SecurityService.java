package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.AuthUser;
import com.business_control_system.domain.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = this.securityRepository.getByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User "+username+" Not found"));

        return User.builder()
                .username(user.username())
                .password(user.password())
                .roles("ADMIN")
                .build();

    }
}
