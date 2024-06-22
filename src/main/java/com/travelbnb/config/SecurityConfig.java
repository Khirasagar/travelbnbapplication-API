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

        http.csrf().disable().cors().disable(); //H cd^2  (h cd square)
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        //http.authorizeHttpRequests().anyRequest().permitAll();       //(haap)


        //which url can be accessed by whom ..Authorization of url
        //harpic
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/users/login" , "/api/v1/users/createUser").permitAll()
                .requestMatchers("/api/v1/countries/addCountry").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }
}
