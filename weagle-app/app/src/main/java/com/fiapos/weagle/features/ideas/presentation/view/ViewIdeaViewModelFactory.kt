package com.fiapos.weagle.features.ideas.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository

class ViewIdeaViewModelFactory(
    private val ideaRepository: IdeaRepository,
    private val sessionManager: SessionManager,
    private val ideaId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewIdeaViewModel::class.java)) {
            return ViewIdeaViewModel(
                ideaRepository,
                sessionManager,
                ideaId
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}