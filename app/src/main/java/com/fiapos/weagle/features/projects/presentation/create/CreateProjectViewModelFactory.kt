package com.fiapos.weagle.features.projects.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.features.projects.data.ProjectRepository

class CreateProjectViewModelFactory(
    private val projectRepository: ProjectRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateProjectViewModel::class.java)) {
            return CreateProjectViewModel(
                projectRepository,
                sessionManager
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}