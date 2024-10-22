package com.bolsadeideas.spring.boot.apirest.infrastructure.jwt;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JwtValuesStatic {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}

