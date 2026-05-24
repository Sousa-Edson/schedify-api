package com.schedify.schedify_api.infrastructure.repository;

import com.schedify.schedify_api.infrastructure.persistence.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
}
