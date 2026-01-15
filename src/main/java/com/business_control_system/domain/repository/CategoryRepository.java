package com.business_control_system.domain.repository;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryRepository {
    Page<Category> getAll(int page, int elements);
    Category getById(int id);
    Category create(CategoryRequest request);
    Category update(int id, CategoryRequest request);
}
