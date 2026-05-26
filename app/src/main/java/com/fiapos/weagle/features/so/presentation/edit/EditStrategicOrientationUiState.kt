package com.fiapos.weagle.features.so.presentation.edit

sealed class EditStrategicOrientationUiState {

    object Idle: EditStrategicOrientationUiState()

    object Loading: EditStrategicOrientationUiState()

    object Success: EditStrategicOrientationUiState()

    data class Error(
        val message: String
    ): EditStrategicOrientationUiState()
}