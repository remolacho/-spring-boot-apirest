package com.bolsadeideas.spring.boot.apirest.app.controllers;

import com.bolsadeideas.spring.boot.apirest.app.exceptions.ArgumentErrorException;
import com.bolsadeideas.spring.boot.apirest.app.exceptions.ForbiddenException;
import com.bolsadeideas.spring.boot.apirest.app.exceptions.RecordNotFoundException;
import com.bolsadeideas.spring.boot.apirest.app.services.users.IUserService;
import com.bolsadeideas.spring.boot.apirest.domain.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class ApplicationController {
    protected User currentUser;

    public static final String PREFIX_ERROR = "error";

    @Autowired
    private IUserService userService;

    public void initCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        Optional<User> optionalUser = userService.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("The user not found");
        }

        currentUser = optionalUser.orElseThrow(() ->  new RecordNotFoundException("The user not found"));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(PREFIX_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(ArgumentErrorException.class)
    public ResponseEntity<?> handlerArgumentErrorException(ArgumentErrorException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(PREFIX_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handlerForbiddenException(ForbiddenException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(PREFIX_ERROR, ex.getMessage()));
    }
}
