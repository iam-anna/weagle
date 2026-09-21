package com.weagle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "strategy_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyHistory {
    @Id
    private String id;
    private String strategyId;
    private String name;
    private String description;
    private String category;
    private String campaign;
    private String action;
    private LocalDateTime recordedAt;
}
