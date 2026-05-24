package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Bloqueio;
import com.schedify.schedify_api.infrastructure.persistence.entity.BloqueioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BloqueioEntityMapper {

    Bloqueio toDomain(BloqueioEntity entity);

    BloqueioEntity toEntity(Bloqueio domain);

}
