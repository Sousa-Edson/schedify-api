package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.infrastructure.persistence.entity.AgendamentoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T19:56:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class AgendamentoEntityMapperImpl implements AgendamentoEntityMapper {

    @Override
    public Agendamento toDomain(AgendamentoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Agendamento agendamento = new Agendamento();

        agendamento.setDataHoraInicio( entity.getDataHoraInicio() );
        agendamento.setDataHoraFim( entity.getDataHoraFim() );

        agendamento.setStatus( stringToStatus(entity.getStatus()) );

        return agendamento;
    }

    @Override
    public AgendamentoEntity toEntity(Agendamento domain) {
        if ( domain == null ) {
            return null;
        }

        AgendamentoEntity agendamentoEntity = new AgendamentoEntity();

        agendamentoEntity.setId( domain.getId() );
        agendamentoEntity.setUsuarioId( domain.getUsuarioId() );
        agendamentoEntity.setServicoId( domain.getServicoId() );
        agendamentoEntity.setProfissionalId( domain.getProfissionalId() );
        agendamentoEntity.setDataHoraInicio( domain.getDataHoraInicio() );
        agendamentoEntity.setDataHoraFim( domain.getDataHoraFim() );
        agendamentoEntity.setMotivoCancelamento( domain.getMotivoCancelamento() );

        agendamentoEntity.setStatus( statusToString(domain.getStatus()) );

        return agendamentoEntity;
    }
}
