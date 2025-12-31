package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserListCrudRepository extends ListCrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
