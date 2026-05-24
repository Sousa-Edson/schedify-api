package com.schedify.schedify_api.infrastructure.repository;

import com.schedify.schedify_api.infrastructure.persistence.DisponibilidadeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadeJpaRepository extends JpaRepository<DisponibilidadeEntity, Long> {

    List<DisponibilidadeEntity> findByDiaSemana(Integer diaSemana);
}
