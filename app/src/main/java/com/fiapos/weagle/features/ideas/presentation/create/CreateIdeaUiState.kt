package com.fiapos.weagle.features.ideas.presentation.create

sealed class CreateIdeaUiState {
    object Idle : CreateIdeaUiState()

    object Loading : CreateIdeaUiState()

    data class Success(
        val ideaId: String
    ): CreateIdeaUiState()

    data class Error(
        val message: String
    ): CreateIdeaUiState()
}