package com.fiapos.weagle.features.projects.presentation.create

sealed class CreateProjectUiState {
    object Idle : CreateProjectUiState()

    object Loading : CreateProjectUiState()

    object Success : CreateProjectUiState()

    data class Error(
        val message: String
    ): CreateProjectUiState()
}