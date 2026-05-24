package com.schedify.schedify_api.infrastructure.persistence.repository;

import com.schedify.schedify_api.infrastructure.persistence.entity.DisponibilidadeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadeJpaRepository extends JpaRepository<DisponibilidadeEntity, Long> {

    List<DisponibilidadeEntity> findByDiaSemana(Integer diaSemana);

    List<DisponibilidadeEntity> findByProfissionalIdAndDiaSemana(Long profissionalId, Integer diaSemana);

}
