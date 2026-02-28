package com.business_control_system.domain.repository;

import com.business_control_system.perisistence.entity.UserEntity;

import java.util.Optional;

public interface SecurityRepository {
    Optional<UserEntity> getByEmail(String email);
}