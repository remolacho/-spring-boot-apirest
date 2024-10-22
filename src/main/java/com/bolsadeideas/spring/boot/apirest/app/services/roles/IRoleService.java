package com.bolsadeideas.spring.boot.apirest.app.services.roles;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Role;

import java.util.Optional;

public interface IRoleService {
    public Role save(Role role);
    public Optional<Role> findByCode(String code);
}
