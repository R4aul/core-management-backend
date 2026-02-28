package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.AuthUser;
import com.business_control_system.domain.repository.SecurityRepository;
import com.business_control_system.perisistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserEntity user = this.securityRepository.getByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new AuthUser(user);
    }
}
