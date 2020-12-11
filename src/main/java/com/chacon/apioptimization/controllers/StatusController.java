package com.chacon.apioptimization.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    
    private String status = "online";

    @GetMapping(value = "/")
    public String getStatus(){
        return this.status;
    }

}
