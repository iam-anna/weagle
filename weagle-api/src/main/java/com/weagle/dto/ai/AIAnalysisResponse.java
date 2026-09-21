package com.weagle.dto.ai;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AIAnalysisResponse (
        String ideaId,

        @Min(0)
        @Max(100)
        int score,

        @NotBlank
        String justification,

        @NotBlank
        String suggestTitle,

        @NotBlank
        String suggestedDescription,
        
        boolean suggestedPriority
) { }
