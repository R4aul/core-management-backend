package com.business_control_system.domain.repository;

import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductRepository {
    Page<Product> getAll(int page, int elements);
    Product getById(int id);
    Product create(CreateProductRequest createProductRequest);
    Product update(int id, UpdateProductRequest updateProductDTO);
    boolean delete(int id);
}
