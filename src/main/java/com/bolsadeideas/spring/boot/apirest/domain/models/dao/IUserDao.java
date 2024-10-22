package com.bolsadeideas.spring.boot.apirest.domain.models.dao;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserDao extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
