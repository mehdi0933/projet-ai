package com.formationspring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // CORS pour /user/**
                registry.addMapping("/user/**")
                        .allowedOrigins(
                                "http://jiraws-frontend-ia.s3-website.eu-west-3.amazonaws.com",
                                "http://13.38.20.124:8080"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);


                // CORS pour /ai/**
                registry.addMapping("/ai/**")
                        .allowedOrigins(
                                "http://jiraws-frontend-ia.s3-website.eu-west-3.amazonaws.com",
                                "http://13.38.20.124:8080"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);

                registry.addMapping("/ai/**")
                        .allowedOrigins(
                                "https://jiraws-frontend-ia.s3-website.eu-west-3.amazonaws.com",
                                "https://13.38.20.124:8080" // si tu passes le backend en HTTPS
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);

            }

        };
    }
}
