package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.infrastructure.persistence.entity.AgendamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoEntityMapper {

    Agendamento toDomain(AgendamentoEntity entity);

    AgendamentoEntity toEntity(Agendamento domain);

}
