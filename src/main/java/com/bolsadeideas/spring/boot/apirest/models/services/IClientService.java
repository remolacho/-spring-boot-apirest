package com.bolsadeideas.spring.boot.apirest.models.services;

import com.bolsadeideas.spring.boot.apirest.models.entity.Client;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
}
