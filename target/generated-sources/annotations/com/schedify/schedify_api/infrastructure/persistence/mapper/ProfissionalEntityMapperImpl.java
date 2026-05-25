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
public class ProfissionalEntityMapperImpl implements ProfissionalEntityMapper {

    @Override
    public Profissional toDomain(ProfissionalEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Profissional profissional = new Profissional();

        profissional.setNome( entity.getNome() );
        if ( entity.getAtivo() != null ) {
            profissional.setAtivo( entity.getAtivo() );
        }
        if ( entity.getServicos() != null ) {
            Set<Servico> set = servicoEntitySetToServicoSet( entity.getServicos() );
            if ( set != null ) {
                profissional.setServicos( set );
            }
        }

        return profissional;
    }

    @Override
    public ProfissionalEntity toEntity(Profissional domain) {
        if ( domain == null ) {
            return null;
        }

        ProfissionalEntity profissionalEntity = new ProfissionalEntity();

        profissionalEntity.setId( domain.getId() );
        profissionalEntity.setNome( domain.getNome() );
        profissionalEntity.setAtivo( domain.isAtivo() );

        return profissionalEntity;
    }

    @Override
    public Servico servicoEntityToServico(ServicoEntity servicoEntity) {
        if ( servicoEntity == null ) {
            return null;
        }

        Servico servico = new Servico();

        servico.setNome( servicoEntity.getNome() );
        if ( servicoEntity.getDuracaoMinutos() != null ) {
            servico.setDuracaoMinutos( servicoEntity.getDuracaoMinutos() );
        }
        if ( servicoEntity.getBufferMinutos() != null ) {
            servico.setBufferMinutos( servicoEntity.getBufferMinutos() );
        }

        return servico;
    }

    @Override
    public ServicoEntity servicoToServicoEntity(Servico servico) {
        if ( servico == null ) {
            return null;
        }

        ServicoEntity servicoEntity = new ServicoEntity();

        servicoEntity.setId( servico.getId() );
        servicoEntity.setNome( servico.getNome() );
        servicoEntity.setDuracaoMinutos( servico.getDuracaoMinutos() );
        servicoEntity.setBufferMinutos( servico.getBufferMinutos() );

        return servicoEntity;
    }

    protected Set<Servico> servicoEntitySetToServicoSet(Set<ServicoEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Servico> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServicoEntity servicoEntity : set ) {
            set1.add( servicoEntityToServico( servicoEntity ) );
        }

        return set1;
    }

}
