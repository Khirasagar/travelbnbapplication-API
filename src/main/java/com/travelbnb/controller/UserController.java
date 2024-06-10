package com.travelbnb.controller;

import com.travelbnb.entity.AppUser;
import com.travelbnb.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private AppUserRepository appUserRepository;

    public UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser user){

        if(appUserRepository.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("Email already exists!!!",HttpStatus.BAD_REQUEST);
        }
        if(appUserRepository.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Username already exists!!!",HttpStatus.BAD_REQUEST);
        }
        AppUser createdUser = appUserRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
