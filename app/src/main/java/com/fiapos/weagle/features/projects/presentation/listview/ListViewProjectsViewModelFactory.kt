package com.fiapos.weagle.features.projects.presentation.listview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.projects.data.ProjectRepository

class ListViewProjectsViewModelFactory(
    private val repository: ProjectRepository,
) :ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewProjectsViewModel::class.java)) {
            return ListViewProjectsViewModel(repository) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}