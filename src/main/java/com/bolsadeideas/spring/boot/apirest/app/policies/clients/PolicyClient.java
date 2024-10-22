package com.bolsadeideas.spring.boot.apirest.app.policies.clients;

import com.bolsadeideas.spring.boot.apirest.app.exceptions.ForbiddenException;
import com.bolsadeideas.spring.boot.apirest.app.policies.Base;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;

public class PolicyClient extends Base {
    public PolicyClient(User currentUser) {
        super(currentUser);
    }

    public void hasAccess(){
        if(isAdmin()){
            return;
        }

        throw new ForbiddenException("Has not access!!!");
    }
}
