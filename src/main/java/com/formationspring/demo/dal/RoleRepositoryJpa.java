package com.formationspring.demo.dal;

import com.formationspring.demo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAllByName(String name);
}
