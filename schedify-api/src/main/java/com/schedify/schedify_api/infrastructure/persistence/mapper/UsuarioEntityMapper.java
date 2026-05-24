package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.entity.Usuario;
import com.schedify.schedify_api.infrastructure.persistence.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    Usuario toDomain(UsuarioEntity entity);

    UsuarioEntity toEntity(Usuario domain);
}
