package com.fiapos.weagle.features.projects.presentation.create

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.domain.models.Project
import com.fiapos.weagle.domain.models.ProjectStatus
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaUiState
import com.fiapos.weagle.features.projects.data.ProjectRepository
import java.time.LocalDate
import java.util.UUID

class CreateProjectViewModel(
    private val repository: ProjectRepository,
    private val sessionManager: SessionManager,
) : ViewModel() {
    var uiState by mutableStateOf<CreateProjectUiState>(
        CreateProjectUiState.Idle
    )
        private set

    fun createProject(
        name: String,
        description: String,
        isActive: Boolean,
        startDate: LocalDate,
        endDate: LocalDate,
        investment: Float,
        ideaList: List<Idea> = mutableListOf()
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

        val user = sessionManager.getUser()

        if (user == null) {
            uiState = CreateProjectUiState.Error(
                "User not authenticated"
            )
            return
        }

        uiState = CreateProjectUiState.Loading

        val project = Project(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            status = ProjectStatus.ACTIVE,
            startDate = startDate,
            endDate = endDate,
            investment = investment,
            ideaList = ideaList
        )

        repository.createProject(project)

        uiState = CreateProjectUiState.Success(
            projectId = project.id
        )
    }
}