package com.business_control_system.perisistence;

import com.business_control_system.domain.NotFoundException;
import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import com.business_control_system.perisistence.entity.CategoryEntity;
import com.business_control_system.perisistence.entity.ProductEntity;
import com.business_control_system.perisistence.mapper.ProductMapper;
import com.business_control_system.perisistence.repository.CategoryListCrudRepository;
import com.business_control_system.perisistence.repository.ProductListCrudRepository;
import com.business_control_system.perisistence.repository.ProductPagSortRepository;
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
class ProductEntityRepositoryTest {

    @Mock
    private ProductPagSortRepository productPagSortRepository;

    @Mock
    private CategoryListCrudRepository categoryListCrudRepository;

    @Mock
    private ProductListCrudRepository productListCrudRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductEntityRepository productEntityRepository;

    public CreateProductRequest CREATE_REQUEST = new CreateProductRequest(
            "Mouse",
            "Desc",
            12.22,
            3,
            1
    );

    public Product PRODUCT_PREPARED = new Product(
            1,
            "Mouse",
            "Desc",
            12.22
    );

    public UpdateProductRequest UPDATE_PRODUCT_REQUEST = new UpdateProductRequest(
            "Keyboard",
            "Desc",
            12.22,
            3,
            1
    );

    public CategoryEntity categoryEntity = CategoryEntity.builder()
            .id(1)
            .name("Electronics")
            .description("Desc")
            .build();


    public ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("Mouse")
            .description("Desc")
            .price(12.22)
            .stock(3)
            .category(categoryEntity)
            .build();



    @Test
    void GetAllProductRepository() {
        Page<ProductEntity> page = new PageImpl<>(List.of(productEntity));
        Pageable pageable = PageRequest.of(0,9);
        Mockito.when(productPagSortRepository.findAll(pageable))
                .thenReturn(page);
        Mockito.when(productMapper.toDTO(productEntity))
                .thenReturn(PRODUCT_PREPARED);

        Page<Product> result = productEntityRepository.getAll(0,9);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void GetProductByIdRepository() {
        Mockito.when(productListCrudRepository.findById(1))
                .thenReturn(Optional.of(productEntity));
        Mockito.when(productMapper.toDTO(productEntity))
                .thenReturn(PRODUCT_PREPARED);

        Product result = productEntityRepository.getById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void CreateProductRepository() {
    }

    @Test
    void UpdateProductByIdRepository() {
        Mockito.when(categoryListCrudRepository.findById(1))
                .thenReturn(Optional.of(categoryEntity));

        Mockito.when(productListCrudRepository.findById(1))
                .thenReturn(Optional.of(productEntity));

        Mockito.when(productListCrudRepository.save(productEntity))
                .thenReturn(productEntity);

        Mockito.when(productMapper.toDTO(productEntity))
                .thenReturn(PRODUCT_PREPARED);

        Product result = productEntityRepository.update(1, UPDATE_PRODUCT_REQUEST);

        assertEquals("Mouse", result.getName());
    }

    @Test
    void DeleteProductByIdRepository() {
        Mockito.when(productListCrudRepository.existsById(1))
                .thenReturn(true);

        boolean result = productEntityRepository.delete(1);

        assertTrue(result);
        Mockito.verify(productListCrudRepository).deleteById(1);
    }

    @Test
    void NotFoundExceptionRepository(){
        Mockito.when(productListCrudRepository.existsById(1))
                .thenReturn(false);

        assertThrows(
                NotFoundException.class,
                ()-> productEntityRepository.delete(1)
        );
    }
}