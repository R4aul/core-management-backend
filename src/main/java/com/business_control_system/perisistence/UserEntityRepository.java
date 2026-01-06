package com.business_control_system.perisistence;

import com.business_control_system.domain.dto.AuthUser;
import com.business_control_system.domain.dto.RegisterRequest;
import com.business_control_system.domain.dto.RegisterResponse;
import com.business_control_system.domain.exception.AlreadyExistsException;
import com.business_control_system.domain.exception.NotFoundException;
import com.business_control_system.domain.repository.RegisterUserRepository;
import com.business_control_system.domain.repository.SecurityRepository;
import com.business_control_system.perisistence.entity.RoleEntity;
import com.business_control_system.perisistence.entity.UserEntity;
import com.business_control_system.perisistence.mapper.UserMapper;
import com.business_control_system.perisistence.repository.RoleListCrudRepository;
import com.business_control_system.perisistence.repository.UserListCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserEntityRepository implements SecurityRepository, RegisterUserRepository {

    private final UserListCrudRepository repository;
    private final UserMapper mapper;
    private final RoleListCrudRepository roleListCrudRepository;

    @Autowired
    public UserEntityRepository(
            UserListCrudRepository repository,
            UserMapper mapper,
            RoleListCrudRepository roleListCrudRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.roleListCrudRepository = roleListCrudRepository;
    }

    @Override
    public Optional<AuthUser> getByEmail(String email) {
        UserEntity entity = this.repository.findByEmail(email);
        AuthUser authUser = mapper.toDTO(entity);
        return Optional.ofNullable(mapper.toDTO(entity));
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        UserEntity userEmail = this.repository.findByEmail(request.getEmail());

        if (userEmail != null){
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
