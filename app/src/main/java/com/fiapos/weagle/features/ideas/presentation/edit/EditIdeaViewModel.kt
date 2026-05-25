package com.fiapos.weagle.features.ideas.presentation.edit

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.features.ideas.domains.IdeaType
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class EditIdeaViewModel(
    private val repository: IdeaRepository,
    private val ideaId: String
) : ViewModel() {

    var idea by mutableStateOf<Idea?>(
        null
    )
        private set

    var uiState by mutableStateOf<EditIdeaUiState>(
        EditIdeaUiState.Idle
    )
        private set

    init {
        loadIdea()
    }

    fun loadIdea() {

        viewModelScope.launch {

            idea = repository.getIdeaById(
                ideaId.toInt()
            )
        }
    }

    fun editIdea(
        title: String,
        description: String,
        type: IdeaType
    ) {
        if (title.isBlank() || description.isBlank()) {

            uiState = EditIdeaUiState.Error(
                "Fill all fields"
            )
            return
        }

        uiState = EditIdeaUiState.Loading

        idea = idea?.copy(
            title = title,
            description = description,
            type = type,
            isEdited = true
        )

        viewModelScope.launch {

            try {

                idea?.let {
                    repository.updateIdea(it)
                }

                uiState = EditIdeaUiState.Success
            } catch (e: Exception) {
                EditIdeaUiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}