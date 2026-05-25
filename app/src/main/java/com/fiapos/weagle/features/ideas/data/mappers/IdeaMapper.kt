package com.fiapos.weagle.features.ideas.data.mappers

import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity
import com.fiapos.weagle.features.ideas.domain.Idea
import com.fiapos.weagle.features.ideas.domain.IdeaType
import java.time.Instant
import java.time.ZoneId

fun IdeaEntity.toIdea(): Idea {
    return Idea(
        id = id.toString(),
        title = title,
        description = description,
        type = IdeaType.valueOf(type),
        createdBy = createdBy,
        createdAt = Instant
            .ofEpochMilli(createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        votes = votes
    )
}