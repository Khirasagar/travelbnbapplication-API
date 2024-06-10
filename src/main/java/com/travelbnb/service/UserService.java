package com.travelbnb.service;

import com.travelbnb.entity.AppUser;
import com.travelbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(AppUser user){

       //setting up the pw encryption
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));

        AppUser createdUser = appUserRepository.save(user);
        return createdUser;
    }
}
