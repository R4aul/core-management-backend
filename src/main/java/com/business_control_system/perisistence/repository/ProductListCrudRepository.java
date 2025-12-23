package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.ProductEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductListCrudRepository extends ListCrudRepository<ProductEntity, Integer> {
}
