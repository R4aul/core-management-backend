package com.business_control_system.perisistence;

import com.business_control_system.domain.dto.AuthUser;
import com.business_control_system.domain.repository.SecurityRepository;
import com.business_control_system.perisistence.entity.UserEntity;
import com.business_control_system.perisistence.mapper.UserMapper;
import com.business_control_system.perisistence.repository.UserListCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserEntityRepository implements SecurityRepository {

    private final UserListCrudRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserEntityRepository(UserListCrudRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AuthUser> getByEmail(String email) {
        UserEntity entity = this.repository.findByEmail(email);
        AuthUser authUser = mapper.toDTO(entity);
        return Optional.ofNullable(mapper.toDTO(entity));
    }
}
