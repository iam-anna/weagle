package com.fiapos.weagle.features.projects.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.ProjectRepository
import java.time.LocalDate

class ViewProjectViewModel(
    private val repository: ProjectRepository,
    private val projectId: String
): ViewModel() {

    var project by mutableStateOf<Project?>(
        null
    )
        private set

    init {
        loadProject()
    }

    private fun loadProject() {
        project = Project(
            id = "123",
            name = "Nome do Projeto",
            description = "Descrição do porjetp lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem.",
            status = ProjectStatus.ACTIVE,
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            investment = 1212.0F,
            ideaList = mutableListOf(),
            ownedBy = "John Doew",
            createdAt = LocalDate.now()
        )
    }
}