package com.business_control_system.perisistence;

import com.business_control_system.domain.dto.RegisterRequest;
import com.business_control_system.domain.dto.RegisterResponse;
import com.business_control_system.domain.exception.AlreadyExistsException;
import com.business_control_system.domain.exception.NotFoundException;
import com.business_control_system.domain.repository.RegisterUserRepository;
import com.business_control_system.domain.repository.SecurityRepository;
import com.business_control_system.perisistence.entity.RoleEntity;
import com.business_control_system.perisistence.entity.UserEntity;
import com.business_control_system.perisistence.repository.RoleListCrudRepository;
import com.business_control_system.perisistence.repository.UserListCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserEntityRepository implements SecurityRepository, RegisterUserRepository {

    private final UserListCrudRepository repository;
    private final RoleListCrudRepository roleListCrudRepository;

    @Autowired
    public UserEntityRepository(
            UserListCrudRepository repository,
            RoleListCrudRepository roleListCrudRepository
    ) {
        this.repository = repository;
        this.roleListCrudRepository = roleListCrudRepository;
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (this.repository.findByEmail(request.getEmail()).isPresent()){
            throw new AlreadyExistsException("User with email "+request.getEmail()+" already exists");
        }

        RoleEntity role = this.roleListCrudRepository.findById(2)
                .orElseThrow(()-> new NotFoundException("Role not found"));

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .status(true)
                .build();

        UserEntity userCreated = this.repository.save(user);

        return new RegisterResponse(userCreated.getName(), userCreated.getEmail());
    }
}
