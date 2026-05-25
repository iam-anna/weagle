package com.fiapos.weagle.features.ideas.presentation.create

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.features.ideas.domains.IdeaType
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class CreateIdeaViewModel(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var uiState by mutableStateOf<CreateIdeaUiState>(
        CreateIdeaUiState.Idle
    )
        private set

    fun createIdea(
        title: String,
        description: String,
        type: IdeaType
    ) {
        if (title.isBlank() || description.isBlank()) {
            uiState = CreateIdeaUiState.Error(
                "Fill all fields"
            )
            return
        }

        viewModelScope.launch {
            repository.createIdea(
                title,
                description,
                type,
                sessionManager.getUserId() ?: "Unknown"
            )
        }

//        val user = sessionManager.getUser()

//        if (user == null) {
//            uiState = CreateIdeaUiState.Error(
//                "User not authenticated"
//            )
//            return
//        }

        uiState = CreateIdeaUiState.Loading

        val idea = Idea(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            type = type,
            createdBy = "user.name",
            createdAt = LocalDate.now(),
            votes = 0
        )



        uiState = CreateIdeaUiState.Success(
            "user.id"
        )
    }
}