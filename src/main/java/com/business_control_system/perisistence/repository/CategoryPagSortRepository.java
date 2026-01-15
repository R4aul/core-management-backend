package com.business_control_system.perisistence.repository;

import com.business_control_system.perisistence.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryPagSortRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {
}
