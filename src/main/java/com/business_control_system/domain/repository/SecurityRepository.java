package com.business_control_system.domain.repository;

import com.business_control_system.domain.dto.AuthUser;

import java.util.Optional;

public interface SecurityRepository {
    Optional<AuthUser> getByEmail(String email);
}