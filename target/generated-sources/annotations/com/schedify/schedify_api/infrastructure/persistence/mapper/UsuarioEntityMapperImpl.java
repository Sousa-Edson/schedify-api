package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Usuario;
import com.schedify.schedify_api.infrastructure.persistence.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T19:56:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class UsuarioEntityMapperImpl implements UsuarioEntityMapper {

    @Override
    public Usuario toDomain(UsuarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( entity.getNome() );

        return usuario;
    }

    @Override
    public UsuarioEntity toEntity(Usuario domain) {
        if ( domain == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setId( domain.getId() );
        usuarioEntity.setNome( domain.getNome() );

        return usuarioEntity;
    }
}
