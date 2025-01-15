package com.T4.storyTracks;

import lombok.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public RestClient geminiRestClient() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            System.out.println(properties.getProperty("GEMINI_API_KEY"));
            return RestClient.builder()
                    .baseUrl("https://generativelanguage.googleapis.com")
                    .defaultHeader("x-goog-api-key", properties.getProperty("GEMINI_API_KEY"))
                    .defaultHeader("Content-Type", "application/json")
                    .defaultHeader("Accept", "application/json")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public GeminiInterface geminiInterface(@Qualifier("geminiRestClient") RestClient client) {
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GeminiInterface.class);
    }
}
