package com.business_control_system.perisistence.mapper;

import com.business_control_system.domain.dto.AuthUser;
import com.business_control_system.perisistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "email", target = "username")
    })
    AuthUser toDTO(UserEntity entity);
}
