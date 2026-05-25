package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.infrastructure.persistence.entity.ProfissionalEntity;
import com.schedify.schedify_api.infrastructure.persistence.entity.ServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfissionalEntityMapper {

    Profissional toDomain(ProfissionalEntity entity);

    @Mapping(target = "servicos", ignore = true)
    ProfissionalEntity toEntity(Profissional domain);

    @Mapping(target = "profissionais", ignore = true)
    Servico servicoEntityToServico(ServicoEntity entity);

    @Mapping(target = "profissionais", ignore = true)
    ServicoEntity servicoToServicoEntity(Servico servico);
}
