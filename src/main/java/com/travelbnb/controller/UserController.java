package com.travelbnb.controller;

import com.travelbnb.entity.AppUser;
import com.travelbnb.payload.LoginDto;
import com.travelbnb.repository.AppUserRepository;
import com.travelbnb.service.UserService;
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
    private UserService userService;

    public UserController(AppUserRepository appUserRepository, UserService userService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser user){
            if(appUserRepository.existsByEmail(user.getEmail())){
                return new ResponseEntity<>("Email already exists!!!", HttpStatus.BAD_REQUEST);
            }
            if(appUserRepository.existsByUsername(user.getUsername())){
                return new ResponseEntity<>("Username already exists!!!",HttpStatus.BAD_REQUEST);
            }
        AppUser createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //method for signup feature
    @PostMapping("/login")
    public ResponseEntity<String> verifyLogin(@RequestBody LoginDto loginDto){
        Boolean val = userService.verifyLogin(loginDto);
        if(val){
            return new ResponseEntity<>("Login Successful!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid usename/password!",HttpStatus.OK);
    }

}
