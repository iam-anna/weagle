package com.fiapos.weagle.features.ideas.presentation.listview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository

class ListViewIdeasViewModelFactory(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewIdeasViewModel::class.java)) {
            return ListViewIdeasViewModel(repository, sessionManager) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}