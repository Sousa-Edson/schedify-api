package com.schedify.schedify_api.infrastructure.persistence.repository;

import com.schedify.schedify_api.infrastructure.persistence.entity.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalEntity, Long> {}
