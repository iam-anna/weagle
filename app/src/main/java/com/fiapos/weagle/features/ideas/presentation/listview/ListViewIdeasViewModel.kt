package com.fiapos.weagle.features.ideas.presentation.listview

import androidx.lifecycle.ViewModel
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListViewIdeasViewModel(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var selectedOption = MutableStateFlow("Todas")

    private val _ideas = MutableStateFlow<List<Idea>>(emptyList())
    val ideas: StateFlow<List<Idea>> = _ideas

    init {
        fetchIdeas()
    }

    fun fetchIdeas() {
        val allIdeas = repository.getAllIdeas()

        if (selectedOption.value == "Criadas por mim") {
            val currentUserId = sessionManager.getUserId()

            _ideas.value = allIdeas.filter { it.createdBy == currentUserId }
        } else {
            _ideas.value = allIdeas
        }
    }

    fun loadIdeas(): MutableList<Idea> {
        return repository.getAllIdeas()
    }
}