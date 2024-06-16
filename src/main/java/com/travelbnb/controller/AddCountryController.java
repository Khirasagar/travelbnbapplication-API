package com.travelbnb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
public class AddCountryController {

    @PostMapping("/addCountry")
    public String addCountry(){
        return "Done";
    }

}


