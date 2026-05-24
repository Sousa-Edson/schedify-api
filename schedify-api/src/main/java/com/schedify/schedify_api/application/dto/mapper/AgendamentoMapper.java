package com.schedify.schedify_api.application.dto.mapper;

import com.schedify.schedify_api.application.dto.AgendamentoDTO;
import com.schedify.schedify_api.domain.entity.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(target = "nomeUsuario", ignore = true)
    @Mapping(target = "nomeServico", ignore = true)
    AgendamentoDTO toDTO(Agendamento agendamento);
}
