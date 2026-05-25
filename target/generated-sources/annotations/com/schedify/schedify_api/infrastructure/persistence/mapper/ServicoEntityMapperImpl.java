package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.infrastructure.persistence.entity.ProfissionalEntity;
import com.schedify.schedify_api.infrastructure.persistence.entity.ServicoEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T20:40:00-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ServicoEntityMapperImpl implements ServicoEntityMapper {

    @Override
    public Servico toDomain(ServicoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Servico servico = new Servico();

        servico.setNome( entity.getNome() );
        if ( entity.getDuracaoMinutos() != null ) {
            servico.setDuracaoMinutos( entity.getDuracaoMinutos() );
        }
        if ( entity.getBufferMinutos() != null ) {
            servico.setBufferMinutos( entity.getBufferMinutos() );
        }

        return servico;
    }

    @Override
    public ServicoEntity toEntity(Servico domain) {
        if ( domain == null ) {
            return null;
        }

        ServicoEntity servicoEntity = new ServicoEntity();

        servicoEntity.setId( domain.getId() );
        servicoEntity.setNome( domain.getNome() );
        servicoEntity.setDuracaoMinutos( domain.getDuracaoMinutos() );
        servicoEntity.setBufferMinutos( domain.getBufferMinutos() );

        return servicoEntity;
    }

    @Override
    public Profissional profissionalEntityToProfissional(ProfissionalEntity profissionalEntity) {
        if ( profissionalEntity == null ) {
            return null;
        }

        Profissional profissional = new Profissional();

        profissional.setNome( profissionalEntity.getNome() );
        if ( profissionalEntity.getAtivo() != null ) {
            profissional.setAtivo( profissionalEntity.getAtivo() );
        }

        return profissional;
    }

    @Override
    public ProfissionalEntity profissionalToProfissionalEntity(Profissional profissional) {
        if ( profissional == null ) {
            return null;
        }

        ProfissionalEntity profissionalEntity = new ProfissionalEntity();

        profissionalEntity.setId( profissional.getId() );
        profissionalEntity.setNome( profissional.getNome() );
        profissionalEntity.setAtivo( profissional.isAtivo() );

        return profissionalEntity;
    }

}
