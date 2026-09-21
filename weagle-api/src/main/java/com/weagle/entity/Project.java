package com.weagle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private String id;

    private String name;

    private String description;

    private String strategyId;

    private String ideaId;

    private int progress;

    private String results;

    private double investment;

    private double financialReturn;

    private double productivityIncrease;

    private double costReduction;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
