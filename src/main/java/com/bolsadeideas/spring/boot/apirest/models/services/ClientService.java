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
}
