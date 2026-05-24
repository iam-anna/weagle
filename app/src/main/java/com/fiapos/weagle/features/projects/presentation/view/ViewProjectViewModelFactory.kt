package com.fiapos.weagle.features.projects.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.projects.data.ProjectRepository

class ViewProjectViewModelFactory(
    private val projectRepository: ProjectRepository,
    private val projectId: String,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewProjectViewModel::class.java)) {
            return ViewProjectViewModel(
                projectRepository,
                projectId
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}