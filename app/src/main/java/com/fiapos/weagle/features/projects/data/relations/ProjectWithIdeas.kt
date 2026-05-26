package com.fiapos.weagle.features.projects.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity
import com.fiapos.weagle.features.projects.data.entities.ProjectEntity

data class ProjectWithIdeas (

    @Embedded
    val project: ProjectEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "projectId"
    )
    val ideas: List<IdeaEntity>
)