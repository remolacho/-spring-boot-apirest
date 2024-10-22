package com.bolsadeideas.spring.boot.apirest.app.policies;

import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;

public class Base {
    protected final User currentUser;

    public Base(User currentUser) {
        this.currentUser = currentUser;
    }

    protected boolean isAdmin(){
       return currentUser.getRoles().stream()
               .anyMatch(role -> role.getCode().equals("admin"));
    }
}
