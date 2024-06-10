package com.travelbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//Program to demo encrypt the password using PasswordEncoder
public class A {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("testing"));
        System.out.println(BCrypt.hashpw("testing", BCrypt.gensalt(10)));
    }

}
