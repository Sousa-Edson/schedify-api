package com.schedify.schedify_api.application.dto.mapper;

import com.schedify.schedify_api.application.dto.UsuarioDTO;
import com.schedify.schedify_api.domain.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);
}
