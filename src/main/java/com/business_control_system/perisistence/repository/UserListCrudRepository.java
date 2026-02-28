package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserListCrudRepository extends ListCrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
