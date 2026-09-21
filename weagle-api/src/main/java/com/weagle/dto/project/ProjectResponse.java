package com.weagle.dto.project;

import com.weagle.entity.Project;
import com.weagle.entity.ProjectStatus;
import java.time.LocalDateTime;

public record ProjectResponse(
        String id,
        String name,
        String description,
        String strategyId,
        String ideaId,
        ProjectStatus status,
        int progress,
        double investment,
        double financialReturn,
        double productivityIncrease,
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
                project.getFinancialReturn(),
                project.getProductivityIncrease(),
                project.getCostReduction(),
                project.getResults(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
