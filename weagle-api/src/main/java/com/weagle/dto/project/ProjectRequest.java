package com.weagle.dto.project;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(

        @NotBlank
        String name,

        @NotBlank
        String description,

        String strategyId,

        String ideaId,

        @Min(0)
        @Max(100)
        int progress,

        String results,

        double investment,

        double financialReturn,

        double productivityIncrease,

        double costReduction
) { }
