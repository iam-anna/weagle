package com.fiapos.weagle.features.projects.data.mappers

import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.entities.ProjectEntity
import java.time.Instant
import java.time.ZoneId

fun ProjectEntity.toProject(): Project {
    return Project(
        id = id.toString(),
        name = name,
        description = description,
        status = ProjectStatus.valueOf(status),
        startDate = Instant
            .ofEpochMilli(startDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        endDate = Instant
            .ofEpochMilli(endDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        investment = investment,
        ideaList = emptyList(),
        ownedBy = ownedBy,
        createdAt = Instant
        .ofEpochMilli(createdAt)
        .atZone(ZoneId.systemDefault())
        .toLocalDate(),
    )
}