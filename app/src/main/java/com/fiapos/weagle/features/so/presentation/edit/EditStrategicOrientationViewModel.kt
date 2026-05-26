package com.fiapos.weagle.features.so.presentation.edit

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import kotlinx.coroutines.launch

class EditStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository,
    private val orientationId: String
) : ViewModel() {

    var orientation by mutableStateOf<StrategicOrientation?>(
        null
    )
        private set

    var uiState by mutableStateOf<EditStrategicOrientationUiState>(
        EditStrategicOrientationUiState.Idle
    )
        private set

    init {
        loadStrategicOrientation()
    }

    fun loadStrategicOrientation() {

        viewModelScope.launch {

            orientation = repository.getOrientationById(
                orientationId.toInt()
            )
        }
    }

    fun editStrategicOrientation(
        title: String,
        description: String,
        category: StrategicOrientationCategory,
        status: StrategicOrientationStatus
    ){
        if (title.isBlank() || description.isBlank()) {
            uiState = EditStrategicOrientationUiState.Error(
                "Fill all fields"
            )
            return
        }

        uiState = EditStrategicOrientationUiState.Loading

        orientation = orientation?.copy(
            title = title,
            description = description,
            category = category,
            isActive = status,
            isEdited = true
        )

        viewModelScope.launch {

            try {

                orientation?.let {
                    repository.updateOrientation(it)
                }

                uiState = EditStrategicOrientationUiState.Success
            } catch (e: Exception) {
                EditStrategicOrientationUiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}