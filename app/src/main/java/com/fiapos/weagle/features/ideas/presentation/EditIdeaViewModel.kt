package com.fiapos.weagle.features.ideas.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.data.IdeaRepository
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.domain.models.IdeaType

class EditIdeaViewModel(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var uiState by mutableStateOf<EditIdeaUiState>(
        EditIdeaUiState.Idle
    )
        private set

    fun editIdea(
        id: String,
        title: String,
        description: String,
        type: IdeaType
    ) {
        if (title.isBlank() || description.isBlank()) {
            uiState = EditIdeaUiState.Error("Fill all fields")
            return
        }

        uiState = EditIdeaUiState.Loading

        val user = sessionManager.getUser()
        if (user == null) {
            uiState = EditIdeaUiState.Error("User not authenticated")
            return
        }

        val updatedIdea = Idea(
            id = id,
            title = title,
            description = description,
            type = type,
            createdBy = user.id
        )

        repository.editIdea(updatedIdea)
        uiState = EditIdeaUiState.Success
    }
}