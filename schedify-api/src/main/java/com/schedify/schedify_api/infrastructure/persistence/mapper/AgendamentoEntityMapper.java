package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.model.StatusAgendamento;
import com.schedify.schedify_api.infrastructure.persistence.entity.AgendamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoEntityMapper {

    @Mapping(target = "status", expression = "java(stringToStatus(entity.getStatus()))")
    Agendamento toDomain(AgendamentoEntity entity);

    @Mapping(target = "status", expression = "java(statusToString(domain.getStatus()))")
    AgendamentoEntity toEntity(Agendamento domain);

    default StatusAgendamento stringToStatus(String value) {
        return value == null ? StatusAgendamento.AGENDADO : StatusAgendamento.valueOf(value);
    }

    default String statusToString(StatusAgendamento status) {
        return status == null ? StatusAgendamento.AGENDADO.name() : status.name();
    }

}
