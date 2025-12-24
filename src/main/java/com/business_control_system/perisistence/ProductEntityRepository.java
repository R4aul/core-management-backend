package com.business_control_system.perisistence;

import com.business_control_system.domain.NotFoundException;
import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import com.business_control_system.domain.repository.ProductRepository;
import com.business_control_system.perisistence.entity.CategoryEntity;
import com.business_control_system.perisistence.entity.ProductEntity;
import com.business_control_system.perisistence.mapper.ProductMapper;
import com.business_control_system.perisistence.repository.CategoryListCrudRepository;
import com.business_control_system.perisistence.repository.ProductListCrudRepository;
import com.business_control_system.perisistence.repository.ProductPagSortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductEntityRepository implements ProductRepository {

    private final ProductPagSortRepository productPagSortRepository;
    private final ProductListCrudRepository productListCrudRepository;
    private final CategoryListCrudRepository categoryListCrudRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductEntityRepository(ProductPagSortRepository productPagSortRepository, ProductListCrudRepository productListCrudRepository, CategoryListCrudRepository categoryListCrudRepository, ProductMapper productMapper) {
        this.productPagSortRepository = productPagSortRepository;
        this.productListCrudRepository = productListCrudRepository;
        this.categoryListCrudRepository = categoryListCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Page<Product> getAll(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        Page<ProductEntity> productEntities = this.productPagSortRepository.findAll(pageable);
        return productEntities.map(this.productMapper::toDTO);
    }

    @Override
    public Product getById(int id) {
        ProductEntity productEntity = this.productListCrudRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product Not Found"));
        return this.productMapper.toDTO(productEntity);
    }

    @Override
    public Product create(CreateProductRequest request) {
        CategoryEntity category = this.categoryListCrudRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new NotFoundException("Category Not Found"));
        ProductEntity newProduct = ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .stock(request.getStock())
                .price(request.getPrice())
                .category(category)
                .build();

        return productMapper.toDTO(this.productListCrudRepository.save(newProduct));
    }

    @Transactional
    @Override
    public Product update(int id, UpdateProductRequest request) {
        CategoryEntity category = this.categoryListCrudRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        ProductEntity product = this.productListCrudRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

        return this.productMapper.toDTO(this.productListCrudRepository.save(product));
    }

    @Override
    public boolean delete(int id) {
        if (!this.productListCrudRepository.existsById(id)){
            throw new NotFoundException("Product Not Found");
        }
        this.productListCrudRepository.deleteById(id);
        return true;
    }
}
