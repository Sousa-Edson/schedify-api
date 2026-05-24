package com.schedify.schedify_api.infrastructure.repository;

import com.schedify.schedify_api.infrastructure.persistence.AgendamentoEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoEntity, Long> {

    List<AgendamentoEntity> findByServicoIdAndDataHoraInicioBetween(
            Long servicoId, LocalDateTime inicio, LocalDateTime fim);

    List<AgendamentoEntity> findByUsuarioId(Long usuarioId);
}
