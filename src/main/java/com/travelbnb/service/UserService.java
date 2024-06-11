package com.travelbnb.service;

import com.travelbnb.entity.AppUser;
import com.travelbnb.payload.LoginDto;
import com.travelbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Boolean verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUserName = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUserName.isPresent()){
            AppUser appUser = opUserName.get();
            return BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword());
        }
        return false;
    }
}
