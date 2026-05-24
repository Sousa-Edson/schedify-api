package com.schedify.schedify_api.application.dto.mapper;

import com.schedify.schedify_api.application.dto.ServicoDTO;
import com.schedify.schedify_api.domain.entity.Servico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    ServicoDTO toDTO(Servico servico);
}
