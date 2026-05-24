package com.schedify.schedify_api.infrastructure.persistence.repository;

import com.schedify.schedify_api.infrastructure.persistence.entity.BloqueioEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioJpaRepository extends JpaRepository<BloqueioEntity, Long> {

    @Query("SELECT b FROM BloqueioEntity b WHERE b.profissionalId = :profissionalId AND b.dataInicio < :fim AND b.dataFim > :inicio")
    List<BloqueioEntity> findByProfissionalIdAndPeriodo(@Param("profissionalId") Long profissionalId,
                                                         @Param("inicio") LocalDateTime inicio,
                                                         @Param("fim") LocalDateTime fim);
}
