package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.infrastructure.persistence.entity.DisponibilidadeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T19:56:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class DisponibilidadeEntityMapperImpl implements DisponibilidadeEntityMapper {

    @Override
    public Disponibilidade toDomain(DisponibilidadeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Disponibilidade disponibilidade = new Disponibilidade();

        disponibilidade.setHoraInicio( entity.getHoraInicio() );
        disponibilidade.setHoraFim( entity.getHoraFim() );
        disponibilidade.setProfissionalId( entity.getProfissionalId() );

        disponibilidade.setDiaSemana( integerToDayOfWeek(entity.getDiaSemana()) );

        return disponibilidade;
    }

    @Override
    public DisponibilidadeEntity toEntity(Disponibilidade domain) {
        if ( domain == null ) {
            return null;
        }

        DisponibilidadeEntity disponibilidadeEntity = new DisponibilidadeEntity();

        disponibilidadeEntity.setId( domain.getId() );
        disponibilidadeEntity.setHoraInicio( domain.getHoraInicio() );
        disponibilidadeEntity.setHoraFim( domain.getHoraFim() );
        disponibilidadeEntity.setProfissionalId( domain.getProfissionalId() );

        disponibilidadeEntity.setDiaSemana( dayOfWeekToInteger(domain.getDiaSemana()) );

        return disponibilidadeEntity;
    }
}
