package com.fiapos.weagle.features.ideas.presentation.create

sealed class CreateIdeaUiState {

    object Idle : CreateIdeaUiState()

    object Loading : CreateIdeaUiState()

    object Success : CreateIdeaUiState()

    data class Error(
        val message: String
    ): CreateIdeaUiState()
}