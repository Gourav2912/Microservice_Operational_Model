package com.demo.gourav.helloservice.resource;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${server.port}")
    String port;


    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String hello(){

        return  port;
    }
}

