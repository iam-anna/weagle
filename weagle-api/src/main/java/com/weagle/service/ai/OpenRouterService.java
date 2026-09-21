package com.weagle.service.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weagle.dto.ai.IdeaAnalysisResponse;
import com.weagle.entity.Idea;
import com.weagle.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenRouterService {

    private final IdeaRepository ideaRepository;
    private final ObjectMapper objectMapper;
    private final String apiKey;
    private final String model;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public OpenRouterService(
            IdeaRepository ideaRepository,
            ObjectMapper objectMapper,
            @Value("${openrouter.api-key}") String apiKey,
            @Value("${openrouter.model}") String model
    ) {
        this.ideaRepository = ideaRepository;
        this.objectMapper = objectMapper;
        this.apiKey = apiKey;
        this.model = model;
    }

    public IdeaAnalysisResponse analyze(String ideaId) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Configure a variável OPENROUTER_API_KEY para usar a análise por IA");
        }

        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new IllegalArgumentException("Ideia não encontrada"));

        try {
            String prompt = "Analise esta ideia de inovação e responda SOMENTE JSON válido no formato "
                    + "{\\\"score\\\": número inteiro de 0 a 100, \\\"justification\\\": \\\"justificativa em português\\\"}. "
                    + "Avalie impacto, viabilidade e alinhamento estratégico. Título: "
                    + idea.getTitle() + ". Descrição: " + idea.getDescription();

            String body = objectMapper.createObjectNode()
                    .put("model", model)
                    .set("messages", objectMapper.createArrayNode()
                            .add(objectMapper.createObjectNode()
                                    .put("role", "user")
                                    .put("content", prompt)))
                    .toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .header("HTTP-Referer", "http://localhost:8080")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("OpenRouter retornou HTTP " + response.statusCode());
            }

            String content = objectMapper.readTree(response.body())
                    .path("choices").path(0).path("message").path("content").asText();
            JsonNode analysis = objectMapper.readTree(stripMarkdown(content));
            int score = Math.max(0, Math.min(100, analysis.path("score").asInt()));
            String justification = analysis.path("justification").asText("A IA não retornou uma justificativa.");

            idea.setAiScore(score);
            idea.setAiJustification(justification);
            idea.setHighPriority(score >= 70);
            ideaRepository.save(idea);

            return new IdeaAnalysisResponse(idea.getId(), score, justification, idea.isHighPriority());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("A chamada para o OpenRouter foi interrompida", exception);
        } catch (Exception exception) {
            throw new IllegalStateException("Não foi possível analisar a ideia com IA", exception);
        }
    }

    private String stripMarkdown(String content) {
        return content.replace("```json", "").replace("```", "").trim();
    }
}
