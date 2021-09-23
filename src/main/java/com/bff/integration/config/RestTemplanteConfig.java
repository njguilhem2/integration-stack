package com.bff.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplanteConfig extends RestTemplate {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
