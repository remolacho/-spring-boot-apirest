package com.bolsadeideas.spring.boot.apirest.models.services;
import com.bolsadeideas.spring.boot.apirest.models.dao.IClientDao;
import com.bolsadeideas.spring.boot.apirest.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final IClientDao clientDao;

    @Autowired
    public ClientService(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(long id) {
        clientDao.deleteById(id);
    }

    @Override
    @Transactional
    public Client update(Client client, long id){
        Client currentClient = clientDao.findById(id).orElse(null);

        if (currentClient == null){
            return null;
        }

        currentClient.setName(client.getName());
        currentClient.setLastname(client.getLastname());
        currentClient.setEmail(client.getEmail());
        return clientDao.save(currentClient);
    }
}
