package com.bolsadeideas.spring.boot.apirest.app.services.roles;
import com.bolsadeideas.spring.boot.apirest.domain.models.dao.IRoleDao;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private final IRoleDao roleDao;

    @Autowired
    public RoleService(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findByCode(String code) {
        return roleDao.findByCode(code);
    }
}
