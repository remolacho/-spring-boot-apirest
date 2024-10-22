package com.bolsadeideas.spring.boot.apirest.domain.models.dao;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleDao extends CrudRepository<Role, Long> {
    Optional<Role> findByCode(String code);
}
