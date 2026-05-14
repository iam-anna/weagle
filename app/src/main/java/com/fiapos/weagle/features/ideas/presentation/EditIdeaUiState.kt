package com.fiapos.weagle.features.ideas.presentation

sealed class EditIdeaUiState {
    object Idle : EditIdeaUiState()

    object Loading : EditIdeaUiState()

    object Success: EditIdeaUiState()

    data class Error(
        val message: String
    ): EditIdeaUiState()
}