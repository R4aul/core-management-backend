package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.RoleEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface RoleListCrudRepository extends ListCrudRepository<RoleEntity, Integer> {
}
