package com.victormartins.firstexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHello {

    @GetMapping(value="/testAPI", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testAPI(){
        return "Teste de API com Spring Boot";
    }

}
