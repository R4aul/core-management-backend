package com.business_control_system.perisistence.mapper;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.perisistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toDTO(CategoryEntity entity);
}
