package com.bolsadeideas.spring.boot.apirest.app.services.users;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> findAll();
    public User save(User user);
    public User saveAdmin(User user);
    public Optional<User> findByEmail(String email);
}
