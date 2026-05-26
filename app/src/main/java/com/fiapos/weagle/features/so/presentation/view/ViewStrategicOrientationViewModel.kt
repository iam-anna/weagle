package com.fiapos.weagle.features.so.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import kotlinx.coroutines.launch

class ViewStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository,
    private val sessionManager: SessionManager,
    private val orientationId: String
): ViewModel() {

    var orientation by mutableStateOf<StrategicOrientation?>(
        null
    )
        private set

    var canEdit by mutableStateOf(false)
        private set

    init {
        loadOrientation()
    }

    private fun loadOrientation() {

        viewModelScope.launch {
            orientation = repository.getOrientationById(
                orientationId.toInt()
            )

            val currentUserId = sessionManager.getUserId()

            canEdit = orientation?.createdBy == currentUserId
        }
    }
}

