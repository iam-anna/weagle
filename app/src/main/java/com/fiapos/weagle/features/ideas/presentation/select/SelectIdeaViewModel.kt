package com.fiapos.weagle.features.ideas.presentation.select

import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SelectIdeaViewModel (
        private val repository: IdeaRepository,
        private val sessionManager: SessionManager
    ) : ViewModel() {
        fun loadIdeas(): MutableList<Idea> {
            return repository.getAllIdeas()
        }
    }