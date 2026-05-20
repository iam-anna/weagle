package com.fiapos.weagle.features.ideas.presentation.create

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.domain.models.IdeaType
import java.time.LocalDate
import java.util.Date
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

        val user = sessionManager.getUser()

        if (user == null) {
            uiState = CreateIdeaUiState.Error(
                "User not authenticated"
            )
            return
        }

        uiState = CreateIdeaUiState.Loading

        val idea = Idea(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            type = type,
            createdBy = user.name,
            createdAt = LocalDate.now(),
            votes = 0
        )

        repository.createIdea(idea)

        uiState = CreateIdeaUiState.Success(
            user.id
        )
    }
}