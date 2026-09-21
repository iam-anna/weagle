package com.fiapos.weagle.features.ideas.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.data.domain.models.UserRole
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domain.Idea
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.ideas.domain.IdeaStatus
import kotlinx.coroutines.launch

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

    var canApprove by mutableStateOf(false)
        private set

    var aiAnalysis by mutableStateOf<String?>(null)
        private set

    var aiLoading by mutableStateOf(false)
        private set

    init {
        loadIdea()
    }

    private fun loadIdea() {

        viewModelScope.launch {

            idea = repository.getIdeaById(ideaId)

            val currentUserId = sessionManager.getUserId()

            canEdit = idea?.createdBy == currentUserId

            canApprove = canApproveIdea()

            votes = idea?.votes ?: 0
        }
    }

    fun approveIdea() {

        idea = idea?.copy(
            status = IdeaStatus.APPROVED
        )

        canApprove = canApproveIdea()

        viewModelScope.launch {
            idea?.let {
                idea = repository.approveIdea(it.id) ?: it
            }
        }
    }

    private fun canApproveIdea(): Boolean {
        val currentUserRole = sessionManager.getUserRole()

        if (
            currentUserRole == UserRole.MANAGER &&
            idea?.status == IdeaStatus.PENDING
        ) {
            return true
        }

        return false
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

    fun analyzeWithAi() {
        aiLoading = true
        viewModelScope.launch {
            aiAnalysis = runCatching {
                val result = repository.analyzeIdea(ideaId)
                "Score: ${result.score}/100\n${result.justification}"
            }.getOrElse { it.message ?: "Não foi possível analisar a ideia" }
            aiLoading = false
        }
    }
}