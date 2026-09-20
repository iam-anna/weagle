package com.fiapos.weagle.features.so.presentation.create

sealed class CreateStrategicOrientationUiState {

    object Idle: CreateStrategicOrientationUiState()

    object Loading: CreateStrategicOrientationUiState()

    object Success: CreateStrategicOrientationUiState()

    data class Error(
        val message: String
    ): CreateStrategicOrientationUiState()
}