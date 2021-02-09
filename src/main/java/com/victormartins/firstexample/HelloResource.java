package com.victormartins.firstexample;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@RequestMapping(value = "/hello")
public class HelloResource {

    @GetMapping(value="/sayHello", produces= MediaType.APPLICATION_JSON_VALUE)
    public String hello(){
        return "Hello Teste de REST from Spring Boot";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello2(){
        return "Hello 2";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello3", produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloTest(){
        String id = "12345";
        return id;
    }

}
