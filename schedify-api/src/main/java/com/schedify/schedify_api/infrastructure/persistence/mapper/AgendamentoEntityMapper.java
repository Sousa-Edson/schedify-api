package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.infrastructure.persistence.AgendamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoEntityMapper {

    Agendamento toDomain(AgendamentoEntity entity);

    AgendamentoEntity toEntity(Agendamento domain);
}
