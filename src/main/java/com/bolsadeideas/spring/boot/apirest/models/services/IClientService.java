package com.bolsadeideas.spring.boot.apirest.models.services;

import com.bolsadeideas.spring.boot.apirest.models.entity.Client;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
    public Client save(Client client);
    public Client update(Client client, long id);
    public Client findById(long id);
    public void delete(long id);
}
