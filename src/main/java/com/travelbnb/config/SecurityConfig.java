package com.travelbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration  //annotation helps to automatically load the config file to spring ioc
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.csrf().disable().cors().disable(); //H cd^2  (h cd square)
        http.authorizeHttpRequests().anyRequest().permitAll();       //(haap)
        return http.build();
    }

}
