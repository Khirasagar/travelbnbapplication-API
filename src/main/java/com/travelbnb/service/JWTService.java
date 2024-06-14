package com.travelbnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.travelbnb.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;


    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(AppUser user){ //Computer Engineer is Unemployed
        return JWT.create()
                .withClaim("username", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
