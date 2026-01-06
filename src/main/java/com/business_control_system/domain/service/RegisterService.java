package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.RegisterRequest;
import com.business_control_system.domain.dto.RegisterResponse;
import com.business_control_system.domain.repository.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(RegisterUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request){
        String encodedPassword = this.passwordEncoder.encode(request.getPassword());
        RegisterRequest encodeRequest = new RegisterRequest(request.getName(), request.getEmail(), encodedPassword);
        return this.repository.register(encodeRequest);
    }
}
