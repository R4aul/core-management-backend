package com.business_control_system.perisistence.mapper;

import com.business_control_system.domain.dto.Product;
import com.business_control_system.perisistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toDTO(ProductEntity entity);
    ProductEntity toEntity(Product product);
}
