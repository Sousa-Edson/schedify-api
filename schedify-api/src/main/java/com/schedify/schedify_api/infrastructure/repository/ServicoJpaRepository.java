package com.schedify.schedify_api.infrastructure.repository;

import com.schedify.schedify_api.infrastructure.persistence.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Long> {
}
