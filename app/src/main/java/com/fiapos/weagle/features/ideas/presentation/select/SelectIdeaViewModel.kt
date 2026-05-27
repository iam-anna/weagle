package com.fiapos.weagle.features.ideas.presentation.select

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.ideas.domain.Idea
import kotlinx.coroutines.launch

class SelectIdeaViewModel (
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    var ideas by mutableStateOf<List<Idea>>(
        emptyList()
    )
        private set

    init {
        loadIdeas()
    }

    private fun loadIdeas() {

        viewModelScope.launch {
             ideas = repository.getIdeas()
        }
    }
}