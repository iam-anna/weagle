package com.fiapos.weagle.features.so.presentation.create

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import kotlinx.coroutines.launch

class CreateStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository,
    private val sessionManager: SessionManager,
) : ViewModel() {

    var uiState by mutableStateOf<CreateStrategicOrientationUiState>(
        CreateStrategicOrientationUiState.Idle
    )
        private set

    fun createStrategicOrientation(
        title: String,
        description: String,
        category: StrategicOrientationCategory,
        status: StrategicOrientationStatus
    ) {

        if (title.isBlank() || description.isBlank()) {
            uiState = CreateStrategicOrientationUiState.Error(
                "Fill all fields"
            )
            return
        }

        uiState = CreateStrategicOrientationUiState.Loading

        viewModelScope.launch {

            try {

                repository.createOrientation(
                    title,
                    description,
                    category,
                    status,
                    sessionManager.getUserId() ?: "Unknown"
                )

                uiState = CreateStrategicOrientationUiState.Success
            } catch (e: Exception) {
                CreateStrategicOrientationUiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}