package com.weagle.controller;

import com.weagle.dto.ai.AIAnalysisResponse;
import com.weagle.dto.idea.IdeaRequest;
import com.weagle.dto.idea.IdeaResponse;
import com.weagle.entity.Idea;
import com.weagle.service.AIService;
import com.weagle.service.IdeaService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final AIService aiService;

    public IdeaController(
            IdeaService ideaService,
            AIService aiService
    ) {
        this.ideaService = ideaService;
        this.aiService = aiService;
    }

    @GetMapping
    public List<IdeaResponse> findAll() {
        return ideaService.findAll()
                .stream()
                .map(IdeaResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public IdeaResponse findById(@PathVariable String id) {
        return IdeaResponse.fromEntity(
                ideaService.findById(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdeaResponse create (
            @Valid @RequestBody
            IdeaRequest request,
            Authentication authentication
    ) {

        Idea idea = ideaService.create(
                request.title(),
                request.description(),
                authentication.getName()
        );

        return IdeaResponse.fromEntity(idea);
    }

        @PutMapping("/{id}")
    public IdeaResponse update (
            @PathVariable String id,
            @Valid @RequestBody IdeaRequest request,
            Authentication authentication
    ) {

        Idea idea = ideaService.update(
                id,
                request.title(),
                request.description(),
                authentication.getName()
        );

        return IdeaResponse.fromEntity(idea);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            Authentication authentication
    ) {

        ideaService.delete(
                id,
                authentication.getName()
        );
    }

    @PatchMapping("/{id}/approve")
    public IdeaResponse approve (@PathVariable String id) {

        return IdeaResponse.fromEntity(
                ideaService.approve(id)
        );
    }

    @PatchMapping("/{id}/priority")
    public IdeaResponse priority (
            @PathVariable String id,
            @RequestParam boolean highPriority
    ) {

        return IdeaResponse.fromEntity(
                ideaService.setPriority(id, highPriority)
        );
    }

    @PostMapping("/{id}/ai-analysis")
    public AIAnalysisResponse analyzeWithAI(
            @PathVariable String id
    ) {
        return aiService.analyzeIdea(id);
    }
}
