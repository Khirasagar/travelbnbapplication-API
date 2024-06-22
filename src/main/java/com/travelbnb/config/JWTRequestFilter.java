package com.travelbnb.config;

import com.travelbnb.entity.AppUser;
import com.travelbnb.repository.AppUserRepository;
import com.travelbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTRequestFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader =  request.getHeader("Authorization");
       if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")) {
           System.out.println(tokenHeader);
           String token = tokenHeader.substring(8,tokenHeader.length()-1);
           String username = jwtService.getUserName(token);
           System.out.println(token);
           System.out.println(username);

           Optional<AppUser> opUsername = appUserRepository.findByUsername(username);
           if (opUsername.isPresent()){
               AppUser appUser = opUsername.get();

               //to keep track of current users logged in (Session management)
               UsernamePasswordAuthenticationToken authenticationToken =
                       new UsernamePasswordAuthenticationToken(appUser,null,Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
           }
       }
       filterChain.doFilter(request,response); //call this line when the url is subsequently requested after the previous request
    }
}
