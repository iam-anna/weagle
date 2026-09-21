package com.fiapos.weagle.features.ideas.data

import com.fiapos.weagle.features.ideas.data.dao.IdeaDao
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity
import com.fiapos.weagle.features.ideas.data.mappers.toEntity
import com.fiapos.weagle.features.ideas.data.mappers.toIdea
import com.fiapos.weagle.features.ideas.domain.Idea
import com.fiapos.weagle.features.ideas.domain.IdeaStatus
import com.fiapos.weagle.features.ideas.domain.IdeaType
import com.fiapos.weagle.data.remote.IdeaRequest
import com.fiapos.weagle.data.remote.WeagleApi
import java.time.LocalDate

class IdeaRepository(
    private val dao: IdeaDao,
    private val api: WeagleApi? = null
) {

    suspend fun createIdea(
        title: String,
        description: String,
        type: IdeaType,
        createdBy: String
    ) {

        if (api != null) {
            api.createIdea(IdeaRequest(title, description))
            return
        }

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

        if (api != null) {
            return api.getIdeas().map { response ->
                response.toDomain()
            }
        }

        return dao.getAll()
            .map {
                it.toIdea()
            }
    }

    suspend fun getIdeaById(id: Int): Idea? {
        if (api != null) {
            return runCatching { api.getIdea(id.toString()).toDomain() }.getOrNull()
        }
        return dao.getById(id)?.toIdea()
    }

    suspend fun updateIdea(
        idea: Idea
    ) {
        if (api != null) {
            api.updateIdea(idea.id, IdeaRequest(idea.title, idea.description))
            return
        }
        dao.update(
            idea = idea.toEntity()
        )
    }

    private fun com.fiapos.weagle.data.remote.IdeaResponse.toDomain() = Idea(
        id = id,
        title = title,
        description = description,
        type = IdeaType.IDEA,
        status = if (approved) IdeaStatus.APPROVED else IdeaStatus.PENDING,
        createdBy = createdBy,
        createdAt = createdAt?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrDefault(LocalDate.now()) }
            ?: LocalDate.now(),
        isEdited = false,
        votes = aiScore ?: 0,
        project = ""
    )
}