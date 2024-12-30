package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Разрешить доступ ко всем эндпоинтам
                        .allowedOrigins("http://localhost:4200") // Укажите фронтенд URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные методы
                        .allowedHeaders("*"); // Разрешенные заголовки
            }
        };
    }
}
