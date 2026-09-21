package com.weagle.dto.ai;

public record IdeaAnalysisResponse(
        String ideaId,
        int score,
        String justification,
        boolean highPriority
) { }
