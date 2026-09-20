package com.weagle.service.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;


@Service
public class OpenRouterAIProvider implements AIProvider {

    private final RestClient restClient;
    private final String apiKey;
    private final String model;

    public OpenRouterAIProvider(
            @Value("${ai.api-key}") String apiKey,
            @Value("${ai.model}")  String model
    ) {
        this.apiKey = apiKey;
        this.model = model;

        this.restClient = RestClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + apiKey
                )
                .defaultHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();
    }

    @Override
    public String analyze(String prompt) {

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", new Object[]{
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                }
        );

        Map<?, ?> response = restClient.post()
                .uri("/chat/completions")
                .body(body)
                .retrieve()
                .body(Map.class);

        if (response == null) {
            throw new RuntimeException("Resposta vazia do OpenRouter");
        }

        var choices = (List<?>) response.get("choices");

        if (choices == null || choices.isEmpty()) {
            throw new RuntimeException("OpenRouter não retornou nenhuma resposta");
        }

        var fistChoice = (Map<?, ?>) choices.get(0);
        var message = (Map<?, ?>) fistChoice.get("message");

        return String.valueOf(message.get("content"));
    }
}
