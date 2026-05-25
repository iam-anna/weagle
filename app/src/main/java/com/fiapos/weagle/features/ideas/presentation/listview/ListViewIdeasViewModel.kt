package com.fiapos.weagle.features.ideas.presentation.listview

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewIdeasViewModel(
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

    // TODO: Implement filters
    //    var selectedOption = MutableStateFlow("Todas")
//    fun fetchIdeas() {
//        val allIdeas = repository.getAllIdeas()
//
//        if (selectedOption.value == "Criadas por mim") {
//            val currentUserId = sessionManager.getUserId()
//
//            _ideas.value = allIdeas.filter { it.createdBy == currentUserId }
//        } else {
//            _ideas.value = allIdeas
//        }
//    }

    private fun loadIdeas() {

        viewModelScope.launch {

            ideas = repository.getIdeas()
        }
    }
}