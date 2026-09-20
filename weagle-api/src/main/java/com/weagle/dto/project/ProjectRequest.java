package com.weagle.dto.project;

import com.weagle.entity.ProjectStatus;
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

        ProjectStatus projectStatus,

        @Min(0)
        @Max(100)
        int progress,

        @Min(0)
        double investments,

        @Min(0)
        double returnValue,

        @Min(0)
        double productivityGain,

        @Min(0)
        double costReduction,

        String results
) { }
