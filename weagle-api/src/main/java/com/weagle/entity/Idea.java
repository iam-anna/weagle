package com.weagle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ideas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Idea {

    @Id
    private String id;

    private String title;

    private String description;

    private String createdBy;

    private boolean approved;

    private boolean highPriority;

    private LocalDateTime createdAt;
}
