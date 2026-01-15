package com.business_control_system.perisistence;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.perisistence.entity.CategoryEntity;
import com.business_control_system.perisistence.mapper.CategoryMapper;
import com.business_control_system.perisistence.repository.CategoryListCrudRepository;
import com.business_control_system.perisistence.repository.CategoryPagSortRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryEntityRepositoryTest {

    @Mock
    private CategoryPagSortRepository pagSortRepository;

    @Mock
    private CategoryListCrudRepository repository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryEntityRepository categoryEntityRepository;

    public Category CATEGORY_PREPARED = new Category(1, "name","desc");

    private CategoryEntity CATEGORY_ENTITY = CategoryEntity.builder()
            .id(1)
            .name("name")
            .description("desc")
            .build();

    @Test
    void getAllCategoriesRepository() {
        Page<CategoryEntity> page = new PageImpl<>(List.of(CATEGORY_ENTITY));
        Pageable pageable = PageRequest.of(0,9);

        Mockito.when(pagSortRepository.findAll(pageable))
                .thenReturn(page);

        Page<Category> result = categoryEntityRepository.getAll(0,9);
        assertEquals(1,result.getTotalElements());
    }

    @Test
    void getCategoryByIdRepository() {
        Mockito.when(repository.findById(1))
                .thenReturn(Optional.of(CATEGORY_ENTITY));

        Mockito.when(mapper.toDTO(CATEGORY_ENTITY))
                .thenReturn(CATEGORY_PREPARED);

        Category category = categoryEntityRepository.getById(1);

        assertEquals(1, category.id());
    }

    @Test
    void createCategoryRepository() {

    }

    @Test
    void updateCategoryByIdRepository() {
        Mockito.when(repository.findById(1))
                .thenReturn(Optional.of(CATEGORY_ENTITY));
    }
}