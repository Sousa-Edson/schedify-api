package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Bloqueio;
import com.schedify.schedify_api.infrastructure.persistence.entity.BloqueioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T19:56:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class BloqueioEntityMapperImpl implements BloqueioEntityMapper {

    @Override
    public Bloqueio toDomain(BloqueioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Bloqueio bloqueio = new Bloqueio();

        bloqueio.setDataInicio( entity.getDataInicio() );
        bloqueio.setDataFim( entity.getDataFim() );
        bloqueio.setMotivo( entity.getMotivo() );

        return bloqueio;
    }

    @Override
    public BloqueioEntity toEntity(Bloqueio domain) {
        if ( domain == null ) {
            return null;
        }

        BloqueioEntity bloqueioEntity = new BloqueioEntity();

        bloqueioEntity.setId( domain.getId() );
        bloqueioEntity.setProfissionalId( domain.getProfissionalId() );
        bloqueioEntity.setDataInicio( domain.getDataInicio() );
        bloqueioEntity.setDataFim( domain.getDataFim() );
        bloqueioEntity.setMotivo( domain.getMotivo() );

        return bloqueioEntity;
    }
}
