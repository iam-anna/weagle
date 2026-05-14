package com.fiapos.weagle.features.ideas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.data.IdeaRepository

class EditIdeaViewModelFactory(
    private val ideaRepository: IdeaRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditIdeaViewModel::class.java)) {
            return EditIdeaViewModel(
                ideaRepository,
                sessionManager
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}