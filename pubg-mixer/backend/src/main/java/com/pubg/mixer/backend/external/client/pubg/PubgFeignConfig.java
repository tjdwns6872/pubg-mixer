package com.pubg.mixer.backend.external.client.pubg;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubgFeignConfig {

    @Value("${external.pubg.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer "+apiKey);
            requestTemplate.header("Accept", "application/vnd.api+json");
        };
    }
}
