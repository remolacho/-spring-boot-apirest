package com.bolsadeideas.spring.boot.apirest.domain.models.dao;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
}
