package com.T4.storyTracks;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

    @Bean
    public RestClient geminiRestClient() {

        try{
            // 환경 변수 읽기
            String geminiApiKey = System.getenv("GEMINI_API_KEY");

            // null 체크
            if (geminiApiKey == null) {
                throw new IllegalArgumentException("Environment variable 'GEMINI_API_KEY' is not set or is null.");
            } else {
                return RestClient.builder()
                        .baseUrl("https://generativelanguage.googleapis.com")
                        .defaultHeader("x-goog-api-key", System.getenv("GEMINI_API_KEY"))
                        .defaultHeader("Content-Type", "application/json")
                        .defaultHeader("Accept", "application/json")
                        .build();
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error occurred : " + e.getMessage());
            e.printStackTrace();
            return null;
        }


        /*Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            return RestClient.builder()
                    .baseUrl("https://generativelanguage.googleapis.com")
                    .defaultHeader("x-goog-api-key", System.getenv("GEMINI_API_KEY"))
                    .defaultHeader("Content-Type", "application/json")
                    .defaultHeader("Accept", "application/json")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/
    }

    @Bean
    public GeminiInterface geminiInterface(@Qualifier("geminiRestClient") RestClient client) {
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GeminiInterface.class);
    }
}
