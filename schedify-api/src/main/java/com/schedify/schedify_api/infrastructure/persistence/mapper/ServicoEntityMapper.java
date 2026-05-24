package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.entity.Servico;
import com.schedify.schedify_api.infrastructure.persistence.ServicoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoEntityMapper {

    Servico toDomain(ServicoEntity entity);

    ServicoEntity toEntity(Servico domain);
}
