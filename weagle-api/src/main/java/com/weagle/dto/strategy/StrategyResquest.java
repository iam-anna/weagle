package com.weagle.dto.strategy;

import jakarta.validation.constraints.NotBlank;

public record StrategyResquest(
        @NotBlank String name,
        @NotBlank String description,
        boolean active
) {

}