package com.fiapos.weagle.features.ideas.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.features.ideas.domains.IdeaStatus
import com.fiapos.weagle.features.ideas.domains.IdeaType
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class ViewIdeaViewModel(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager,
    private val ideaId: String
): ViewModel() {

    var idea by mutableStateOf<Idea?>(
        null
    )
        private set

    var votes by mutableStateOf(0)
        private set

    var canEdit by mutableStateOf(false)
        private set

    init {
        loadIdea()
    }

    private fun loadIdea() {

        viewModelScope.launch {

            idea = repository.getIdeaById(
                ideaId.toInt()
            )

            val currentUserId = sessionManager.getUserId()

            canEdit = idea?.createdBy == currentUserId

            votes = idea?.votes ?: 0
        }
    }

    fun upvoteIdea() {

        votes ++

        idea = idea?.copy(
            votes = votes
        )

        viewModelScope.launch {
            idea?.let {

                repository.updateIdea(
                    it
                )
            }
        }
    }

    fun downvoteIdea() {

        votes --

        idea = idea?.copy(
            votes = votes
        )

        viewModelScope.launch {
            idea?.let {

                repository.updateIdea(
                    it
                )
            }
        }
    }
}