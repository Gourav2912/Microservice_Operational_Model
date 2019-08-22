package com.demo.gourav.greetingservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope

public class Controller {

    /*@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }*/



    @Value("${message}")
    private String message;

    @Autowired
    private RestTemplate  restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")


    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String helloclient(){
        String result= restTemplate.getForObject("http://HELLO-SERVICE/hello",String.class);
        return message + result;
    }

    public String fallback(){

        return "This is result from fallback method";
    }
}

@Configuration
 class config {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

