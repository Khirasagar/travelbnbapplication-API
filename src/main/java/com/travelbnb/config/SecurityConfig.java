package com.travelbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //annotation helps to automatically load the config file to spring ioc
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf().disable().cors().disable(); //H cd^2  (h cd square)
        http.authorizeHttpRequests().anyRequest().permitAll();       //(haap)
        return http.build();
    }
}
