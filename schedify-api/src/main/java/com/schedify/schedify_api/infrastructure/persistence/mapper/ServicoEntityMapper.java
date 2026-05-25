package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.infrastructure.persistence.entity.ProfissionalEntity;
import com.schedify.schedify_api.infrastructure.persistence.entity.ServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicoEntityMapper {

    @Mapping(target = "profissionais", ignore = true)
    Servico toDomain(ServicoEntity entity);

    ServicoEntity toEntity(Servico domain);

    @Mapping(target = "servicos", ignore = true)
    Profissional profissionalEntityToProfissional(ProfissionalEntity entity);

    @Mapping(target = "servicos", ignore = true)
    ProfissionalEntity profissionalToProfissionalEntity(Profissional profissional);
}
