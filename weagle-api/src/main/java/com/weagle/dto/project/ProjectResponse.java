package com.weagle.dto.project;

import com.weagle.entity.Project;
import com.weagle.entity.ProjectStatus;
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
        ProjectStatus status,
        int progress,
        double invesment,
        double returnValue,
        double productivityGain,
        double costReduction,
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
                project.getStatus(),
                project.getProgress(),
                project.getInvestment(),
                project.getReturnValue(),
                project.getProductivityGain(),
                project.getCostReduction(),
                project.getResults(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
