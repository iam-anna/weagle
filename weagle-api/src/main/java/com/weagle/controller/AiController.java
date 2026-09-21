package com.weagle.controller;

import com.weagle.dto.ai.IdeaAnalysisResponse;
import com.weagle.service.ai.OpenRouterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final OpenRouterService openRouterService;

    public AiController(OpenRouterService openRouterService) {
        this.openRouterService = openRouterService;
    }

    @PostMapping("/ideas/{id}/analyze")
    public IdeaAnalysisResponse analyzeIdea(@PathVariable String id) {
        return openRouterService.analyze(id);
    }
}
