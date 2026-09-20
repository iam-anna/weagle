package com.weagle.dto.project;

import com.weagle.entity.Project;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ProjectResponse(
        String id,
        String name,
        String description,
        String strategyId,
        String ideaId,
        int progress,
        String results,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProjectResponse fromEntity(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStrategyId(),
                project.getIdeaId(),
                project.getProgress(),
                project.getResults(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
