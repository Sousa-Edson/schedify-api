package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.infrastructure.persistence.entity.ProfissionalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalEntityMapper {

    Profissional toDomain(ProfissionalEntity entity);

    ProfissionalEntity toEntity(Profissional domain);

}
