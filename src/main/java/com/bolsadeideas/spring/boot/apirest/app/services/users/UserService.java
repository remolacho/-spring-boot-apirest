package com.bolsadeideas.spring.boot.apirest.app.services.users;
import com.bolsadeideas.spring.boot.apirest.app.services.roles.IRoleService;
import com.bolsadeideas.spring.boot.apirest.domain.models.dao.IUserDao;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Role;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserDao userDao;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserDao userDao, IRoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> roleUser = roleService.findByCode("user");
        List<Role> roles = new ArrayList<>();
        roleUser.ifPresent(roles::add);

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDao.save(user);
    }

    @Override
    @Transactional
    public User saveAdmin(User user) {
        Optional<Role> roleUser = roleService.findByCode("user");
        List<Role> roles = new ArrayList<>();

        roleUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> roleAdmin = roleService.findByCode("admin");
            roleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
