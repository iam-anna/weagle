package com.weagle.dto.idea;

import com.weagle.entity.Idea;

import java.time.LocalDateTime;

public record IdeaResponse(
        String id,
        String title,
        String description,
        String createdBy,
        boolean approved,
        boolean highPriority,
        Integer aiScore,
        String aiJustification,
        LocalDateTime createdAt
){

    public static IdeaResponse fromEntity(Idea idea) {
        return new IdeaResponse(
                idea.getId(),
                idea.getTitle(),
                idea.getDescription(),
                idea.getCreatedBy(),
                idea.isApproved(),
                idea.isHighPriority(),
                idea.getAiScore(),
                idea.getAiJustification(),
                idea.getCreatedAt()
        );
    }
}
