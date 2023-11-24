package com.tfl.urs.configuration;

import com.tfl.urs.exception.RoadNotFoundErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        RoadNotFoundErrorHandler errorHandler = new RoadNotFoundErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }
}
