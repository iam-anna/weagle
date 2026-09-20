package com.weagle.dto.idea;

import jakarta.validation.constraints.NotBlank;

public record IdeaRequest(
        @NotBlank String title,
        @NotBlank String description
) { }
