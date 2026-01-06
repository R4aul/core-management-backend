package com.business_control_system.domain.repository;

import com.business_control_system.domain.dto.RegisterRequest;
import com.business_control_system.domain.dto.RegisterResponse;

import java.util.Optional;

public interface RegisterUserRepository {
    RegisterResponse register(RegisterRequest request);
}
