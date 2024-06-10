package com.travelbnb.repository;

import com.travelbnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);


}