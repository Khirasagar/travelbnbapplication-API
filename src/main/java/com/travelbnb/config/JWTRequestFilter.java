package com.travelbnb.config;

import com.travelbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    public JWTRequestFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader =  request.getHeader("Authorization");
        System.out.println(tokenHeader);
        String token = tokenHeader.substring(8,tokenHeader.length()-1);
        String username = jwtService.getUserName(token);
        System.out.println(token);
        System.out.println(username);
        filterChain.doFilter(request,response); //call this line when the url is subsequently requested after

    }
}
