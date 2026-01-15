package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import com.business_control_system.domain.repository.CategoryRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    public Category CATEGORY_PREPARED = new Category(1,"category", "desc");

    @Test
    void OBTENER_CATEGORIAS_PAGINADOS() {
        Page<Category> page = new PageImpl<>(List.of(CATEGORY_PREPARED));
        Mockito.when(repository.getAll(0,9)).thenReturn(page);

        Page<Category> result = service.getAll(0,9);
        assertEquals(1, result.getTotalElements());
        Mockito.verify(repository).getAll(0,9);
    }

    @Test
    void OBTENER_PRODUCTO_POR_IDENTIFICADOR() {
        Mockito.when(repository.getById(1)).thenReturn(CATEGORY_PREPARED);
        Category category = service.getById(1);
        assertEquals(1, category.id());
        Mockito.verify(repository).getById(1);
    }

    @Test
    void CREAR_PRODUCTO() {
        CategoryRequest request = new CategoryRequest("category","desc");
        Mockito.when(repository.create(request))
                .thenReturn(CATEGORY_PREPARED);

        Category result = service.create(request);

        assertEquals("category", result.name());
        Mockito.verify(repository).create(request);
    }

    @Test
    void ACTUALIZAR_PRODUCTO() {
        CategoryRequest request = new CategoryRequest("update","desc");
        Category response = new Category(1, "old", "desc");

        Mockito.when(repository.update(1, request))
                .thenReturn(response);

        Category result = service.update(1, request);

        assertEquals(1, result.id());
        Mockito.verify(repository).update(1,request);
    }
}