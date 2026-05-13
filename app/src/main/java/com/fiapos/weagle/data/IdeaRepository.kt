package com.fiapos.weagle.data

import com.fiapos.weagle.domain.models.Idea

class IdeaRepository {
    private val ideas = mutableListOf<Idea>()

    fun createIdea(idea: Idea){
        ideas.add(idea)
    }

    fun getIdeas(): List<Idea>{
        return ideas
    }
}