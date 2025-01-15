package com.T4.storyTracks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
<<<<<<< HEAD
                registry.addMapping("/api/**") // 모든 URL에 대해 CORS 허용
                        .allowedOrigins("http://localhost:3000", "https://storytracks.net") // 허용할 도메인 추가
=======
                registry.addMapping("/**") // 모든 URL에 대해 CORS 허용
                        .allowedOrigins("http://localhost:3000", "http://example.com") // 허용할 도메인 추가
>>>>>>> 435ba72 (Help me - transmission files)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                        .allowedHeaders("*") // 모든 헤더 허용
                        .allowCredentials(true); // 쿠키/인증 정보 허용
            }
        };
    }
}