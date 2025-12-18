package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class student {
    
    @GetMapping("/catfact")
    public String getcat(){
        return "http://catfact.ninja/fact";
    }

    @GetMapping("/dogfact")
    public String getdog(){
        return "https://dog.ceo/api/breeds/image/random";
    }

}
