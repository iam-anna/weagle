package com.fiapos.weagle.features.ideas.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.domain.models.IdeaStatus
import com.fiapos.weagle.domain.models.IdeaType
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class ListIdeasViewModel(
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

//        if (selectedOption.value == "Criadas por mim") {
//            val currentUser = sessionManager.getUser()

//            _ideas.value = allIdeas.filter { it.createdBy == currentUser?.name }
//        } else {
//            _ideas.value = allIdeas
//        }
    }

    fun loadIdeas(): MutableList<Idea> {
        return repository.getAllIdeas()
    }
}