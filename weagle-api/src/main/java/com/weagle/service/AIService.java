package com.weagle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weagle.dto.ai.AIAnalysisResponse;
import com.weagle.entity.Idea;
import com.weagle.service.ai.AIProvider;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final IdeaService ideaService;
    private final AIProvider aiProvider;
    private final ObjectMapper objectMapper;

    public AIService(
            IdeaService ideaService,
            AIProvider aiProvider,
            ObjectMapper objectMapper
    ) {
        this.ideaService = ideaService;
        this.aiProvider = aiProvider;
        this.objectMapper = objectMapper;
    }

    public AIAnalysisResponse analyzeIdea(String ideaId) {

        Idea idea = ideaService.findById(ideaId);

        String prompt = """
            Você é um analista de inovação empresarial.

            Analise a seguinte ideia de melhoria:

            Título:
                %s
                
            Descrição:
                %s
    
            Avalie a ideia considerando:
            - potencial de impacto;
            - viabilidade;
            - potencial de redução de custos;
            - potencial de aumento de produtividade;
            - relevância para a empresa.
                
            Gere um score de 0 a 100 considerando esses critérios.
                
            Defina a prioridade como:
            - true: quando a ideia tiver prioridade alta;
            - false: quando não tiver prioridade alta.
                
            Sugira melhorias para o título e para a descrição.
                
            Retorne SOMENTE um JSON válido, sem markdown,
            sem explicações antes ou depois do JSON.
                
            O JSON deve obrigatoriamente seguir este formato:
                
            {
              "score": 0,
              "justification": "justificativa objetiva",
              "suggestedTitle": "título sugerido",
              "suggestedDescription": "descrição sugerida",
              "suggestedPriority": false
            }
        """.formatted(
                idea.getTitle(),
                idea.getDescription()
        );

        String aiResponse = aiProvider.analyze(prompt);

        try {

            AIAnalysisResponse analysis = objectMapper.readValue(
                    aiResponse,
                    AIAnalysisResponse.class
            );

            if (analysis.justification() == null ||analysis.justification().isBlank()) {
                throw new RuntimeException(
                        "A IA não retornou uma justificativa"
                );
            }

            if (analysis.suggestTitle() == null || analysis.suggestTitle().isBlank()) {
                throw new RuntimeException(
                        "A IA não retornou um título sugerido"
                );
            }

            if (analysis.suggestedDescription() == null || analysis.suggestedDescription().isBlank()) {
                throw new RuntimeException(
                        "A IA não retornou uma descrição sugerida"
                );
            }

            return new AIAnalysisResponse(
                    idea.getId(),
                    analysis.score(),
                    analysis.justification(),
                    analysis.suggestTitle(),
                    analysis.suggestedDescription(),
                    analysis.suggestedPriority()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "A IA retornou uma resposta em formato inválido",
                    e
            );
        }
    }
}
