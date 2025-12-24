package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagSortRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
}
