package com.fiapos.weagle.features.ideas.presentation.create

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.ideas.domain.IdeaType
import kotlinx.coroutines.launch

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

        uiState = CreateIdeaUiState.Loading

        viewModelScope.launch {

            try {

                repository.createIdea(
                    title,
                    description,
                    type,
                    sessionManager.getUserId() ?: "Unknown"
                )

                uiState = CreateIdeaUiState.Success(
                    "user.id"
                )
            } catch(e: Exception) {
                CreateIdeaUiState.Error(
                    e.message ?: "Unknown error"
                )
            }

        }
    }
}