package com.fiapos.weagle.features.ideas.data

import com.fiapos.weagle.domain.models.Idea

object IdeaRepository {
    private val ideas = mutableListOf<Idea>()

    fun createIdea(idea: Idea): Idea {
        ideas.add(idea)
        return idea
    }

    fun getIdeaId(id: String): Idea? {
        return ideas.find {
            it.id == id
        }
    }

    fun editIdea(idea: Idea): Idea {
        val index = ideas.indexOfFirst { it.id == idea.id }

        if (index != -1) {
            ideas[index] = idea
        }

        return idea
    }
}