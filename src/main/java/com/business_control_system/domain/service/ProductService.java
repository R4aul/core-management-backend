package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import com.business_control_system.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(int page, int elements){
        return this.productRepository.getAll(page, elements);
    }

    public Product getById(int id){
        return this.productRepository.getById(id);
    }

    public Product create(CreateProductRequest createProductRequest){
        return this.productRepository.create(createProductRequest);
    }

    public Product update(int id, UpdateProductRequest request){
        return this.productRepository.update(id,request);
    }

    public boolean delete(int id){
        return this.productRepository.delete(id);
    }


}
