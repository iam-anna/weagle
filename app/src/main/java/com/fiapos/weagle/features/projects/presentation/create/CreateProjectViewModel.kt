package com.fiapos.weagle.features.projects.presentation.create

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.domain.Idea
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.ProjectRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class CreateProjectViewModel(
    private val repository: ProjectRepository,
    private val sessionManager: SessionManager,
) : ViewModel() {

    var uiState by mutableStateOf<CreateProjectUiState>(
        CreateProjectUiState.Idle
    )
        private set

    var selectedIdeas by mutableStateOf<List<Idea>>(
        emptyList()
    )
        private set

    fun createProject(
        name: String,
        description: String,
        status: ProjectStatus,
        startDate: LocalDate,
        endDate: LocalDate,
        investment: Float,
        ideaList: List<Int> = mutableListOf()
    ) {
        if (
            name.isBlank() ||
            description.isBlank() ||
            investment.equals(null)
        ) {
            uiState = CreateProjectUiState.Error(
                "Fill all fields"
            )
            return
        }

        uiState = CreateProjectUiState.Loading

        val currentUserId = sessionManager.getUserId() ?: "Unknown"

        viewModelScope.launch {

            try {

                repository.createProject(
                    name,
                    description,
                    status,
                    startDate,
                    endDate,
                    investment,
                    currentUserId,
                    ideaList
                )

                uiState = CreateProjectUiState.Success
            } catch (e: Exception) {
                CreateProjectUiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }

    fun toggleIdeaSelection(idea: Idea) {

        selectedIdeas =
            if (selectedIdeas.any { it.id == idea.id }) {

                selectedIdeas.filterNot {
                    it.id == idea.id
                }

            } else {

                selectedIdeas + idea
            }
    }

    fun isIdeaSelected(ideaId: String): Boolean {

        return selectedIdeas.any {
            it.id == ideaId
        }
    }
}