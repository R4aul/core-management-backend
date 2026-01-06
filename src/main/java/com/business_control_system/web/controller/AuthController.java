package com.business_control_system.web.controller;

import com.business_control_system.domain.dto.LoginRequest;
import com.business_control_system.domain.dto.LoginResponse;
import com.business_control_system.domain.dto.RegisterRequest;
import com.business_control_system.domain.dto.RegisterResponse;
import com.business_control_system.domain.service.RegisterService;
import com.business_control_system.web.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RegisterService registerService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RegisterService registerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
            ){

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        Authentication authentication = this.authenticationManager.authenticate(login);
        String jwt = this.jwtUtil.create(request.username());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.registerService.register(request));
    }
}
