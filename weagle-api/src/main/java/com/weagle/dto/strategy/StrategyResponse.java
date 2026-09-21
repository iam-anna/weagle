package com.weagle.dto.strategy;

import com.weagle.entity.Strategy;

import java.time.LocalDateTime;

public record StrategyResponse(
        String id,
        String name,
        String description,
        boolean active,
        String category,
        String campaign,
        LocalDateTime createdAt
) {

    public static StrategyResponse fromEntity(Strategy strategy) {
        return new StrategyResponse(
                strategy.getId(),
                strategy.getName(),
                strategy.getDescription(),
                strategy.isActive(),
                strategy.getCategory(),
                strategy.getCampaign(),
                strategy.getCreatedAt()
        );
    }
}
