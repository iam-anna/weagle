package com.fiapos.weagle.data

import com.fiapos.weagle.domain.models.Idea

class IdeaRepository {
    private val ideas = mutableListOf<Idea>()

    fun createIdea(idea: Idea): Idea {
        ideas.add(idea)
        return idea
    }

    fun getIdeas(): List<Idea> {
        return ideas
    }

    fun editIdea(idea: Idea): Idea {
        val index = ideas.indexOfFirst { it.id == idea.id }

        if (index != -1) {
            ideas[index] = idea
        }

        return idea
    }
}