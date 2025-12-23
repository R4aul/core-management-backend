package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public Product PRODUCT_PREPARED = new Product(
            1,
            "Mouse",
            "Desc",
            12.22
    );

    @Test
    void OBTENER_TODOS_LOS_PRODUCTOS_PAGINADOS() {
        Page<Product> page = new PageImpl<>(List.of(PRODUCT_PREPARED));
        Mockito.when(productRepository.getAll(0,9)).thenReturn(page);

        Page<Product> resutl = productService.getAll(0,9);

        assertEquals(1, resutl.getTotalElements());
        Mockito.verify(productRepository).getAll(0,9);
    }

    @Test
    void OBTENER_UN_PRODUCTO_POR_SU_ID() {
        Mockito.when(productRepository.getById(1)).thenReturn(PRODUCT_PREPARED);
        Product result = productService.getById(1);
        assertEquals(1, result.getId());
        Mockito.verify(productRepository).getById(1);
    }

    @Test
    void CREAR_UN_SOLO_PRODUCTO() {
        CreateProductRequest request = new CreateProductRequest("Muse",
                "Desc",
                12.33,
                23,
                1
        );

        Product product = new Product(1, "Mouse", "Mouse Desc", 12.22);

        Mockito.when(productRepository.create(request)).thenReturn(product);

        Product productResult = productService.create(request);

        assertEquals("Mouse", productResult.getName());
        Mockito.verify(productRepository).create(request);
    }

/*
    @Test
    void ACTUALIZAR_UN_PRODUCTO_POR_SU_IDENTIFICADOR() {
    }
*/

    @Test
    void ELEMINAR_UN_PRODUCTO() {
        Mockito.when(productRepository.delete(1)).thenReturn(true);
        boolean result = productService.delete(1);
        assertTrue(result);
        Mockito.verify(productRepository).delete(1);

    }
}