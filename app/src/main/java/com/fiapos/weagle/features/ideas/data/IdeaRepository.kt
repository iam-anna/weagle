package com.fiapos.weagle.features.ideas.data

import com.fiapos.weagle.features.ideas.data.dao.IdeaDao
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity
import com.fiapos.weagle.features.ideas.data.mappers.toIdea
import com.fiapos.weagle.features.ideas.domain.Idea
import com.fiapos.weagle.features.ideas.domain.IdeaStatus
import com.fiapos.weagle.features.ideas.domain.IdeaType

class IdeaRepository(
    private val dao: IdeaDao
) {

    suspend fun createIdea(
        title: String,
        description: String,
        type: IdeaType,
        createdBy: String
    ) {

        dao.insert(
            IdeaEntity(
                title = title,
                description = description,
                type = type.name,
                status = IdeaStatus.PENDING.name,
                createdBy = createdBy
            )
        )
    }

    suspend fun getIdeas(): List<Idea> {

        return dao.getAll()
            .map {
                it.toIdea()
            }
    }


    suspend fun getIdeaById(id: Int): Idea? {
        return dao.getById(id)?.toIdea()
    }

    suspend fun updateIdea(
        idea: Idea
    ) {
        dao.update(
            IdeaEntity(
                id = idea.id.toInt(),
                title = idea.title,
                description = idea.description,
                type = idea.type.name,
                status = idea.status.name,
                createdBy = idea.createdBy,
                votes = idea.votes
            )
        )
    }
}