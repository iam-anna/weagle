package com.fiapos.weagle.features.ideas.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.ideas.presentation.select.SelectIdeaViewModel

class SelectIdeaViewModelFactory(
    private val repository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectIdeaViewModel::class.java)) {
            return ListIdeasViewModel(repository, sessionManager) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}