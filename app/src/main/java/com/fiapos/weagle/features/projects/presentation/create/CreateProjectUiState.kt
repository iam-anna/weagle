package com.fiapos.weagle.features.projects.presentation.create

sealed class CreateProjectUiState {
    object Idle : CreateProjectUiState()

    object Loading : CreateProjectUiState()

    data class Success(
        val projectId: String
    ): CreateProjectUiState()

    data class Error(
        val message: String
    ): CreateProjectUiState()
}