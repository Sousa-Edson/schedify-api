package com.schedify.schedify_api.infrastructure.persistence.repository;

import com.schedify.schedify_api.infrastructure.persistence.entity.AgendamentoEntity;
import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity, Long> {

    List<AgendamentoEntity> findByServicoIdAndDataHoraInicioBetween(Long servicoId, LocalDateTime inicio, LocalDateTime fim);

    List<AgendamentoEntity> findByProfissionalIdAndDataHoraInicioBetween(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM AgendamentoEntity a WHERE a.profissionalId = :profissionalId AND a.dataHoraInicio < :fim AND a.dataHoraFim > :inicio")
    List<AgendamentoEntity> findByProfissionalIdAndPeriodoComLock(@Param("profissionalId") Long profissionalId, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    List<AgendamentoEntity> findByUsuarioId(Long usuarioId);

}
