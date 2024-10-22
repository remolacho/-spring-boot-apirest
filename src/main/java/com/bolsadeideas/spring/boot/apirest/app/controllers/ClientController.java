package com.bolsadeideas.spring.boot.apirest.app.controllers;

import com.bolsadeideas.spring.boot.apirest.app.policies.clients.PolicyClient;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.Client;
import com.bolsadeideas.spring.boot.apirest.app.services.clients.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ClientController extends ApplicationController{
    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        super();
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> index(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client show(@PathVariable long id){
        return clientService.findById(id);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        policy().hasAccess();

        return clientService.save(client);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client, @PathVariable Long id){
        policy().hasAccess();

        return clientService.update(client, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id){
        policy().hasAccess();

        clientService.delete(id);
    }

    private PolicyClient policy() {
        return new PolicyClient(currentUser);
    }
}
