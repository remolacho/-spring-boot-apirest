package com.bolsadeideas.spring.boot.apirest.app.controllers;

import com.bolsadeideas.spring.boot.apirest.app.services.users.IUserService;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends ApplicationController{
    private final IUserService userService;

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.save(user);
    }
}
