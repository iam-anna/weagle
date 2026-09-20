package com.fiapos.weagle.features.projects.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.ProjectRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class ViewProjectViewModel(
    private val repository: ProjectRepository,
    private val sessionManager: SessionManager,
    private val projectId: String
): ViewModel() {

    var project by mutableStateOf<Project?>(
        null
    )
        private set

    var canEdit by mutableStateOf(false)
        private set

    init {
        loadProject()
    }

    private fun loadProject() {

        viewModelScope.launch {
            project = repository.getProject(
                projectId.toInt()
            )

            val currentUserId = sessionManager.getUserId()

            canEdit = project?.ownedBy == currentUserId
        }
    }
}