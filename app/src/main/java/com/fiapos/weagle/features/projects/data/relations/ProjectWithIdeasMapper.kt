package com.fiapos.weagle.features.projects.data.relations;

import com.fiapos.weagle.features.ideas.data.mappers.toIdea
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import java.time.Instant
import java.time.ZoneId

fun ProjectWithIdeas.toProject(): Project {
    return Project(
        id = project.id.toString(),
        name = project.name,
        description = project.description,
        status = ProjectStatus.valueOf(project.status),

        startDate = Instant
            .ofEpochMilli(project.startDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),

        endDate = Instant
            .ofEpochMilli(project.endDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),

        investment = project.investment,

        ideaList = ideas.map {
            it.toIdea()
        },

        ownedBy = project.ownedBy,

        createdAt = Instant
            .ofEpochMilli(project.createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    )
}