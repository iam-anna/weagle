package com.fiapos.weagle.features.projects.presentation.listview

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.projects.data.ProjectRepository
import com.fiapos.weagle.features.projects.data.domain.Project
import kotlinx.coroutines.launch

class ListViewProjectsViewModel(
    private val repository: ProjectRepository,
) : ViewModel() {
    var projects by mutableStateOf<List<Project>>(
        emptyList()
    )
        private set

    init {
        loadProjects()
    }

    private fun loadProjects() {

        viewModelScope.launch {

            projects = repository.getProjects()
        }
    }
}