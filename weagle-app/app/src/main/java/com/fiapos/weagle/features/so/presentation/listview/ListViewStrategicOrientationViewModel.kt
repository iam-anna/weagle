package com.fiapos.weagle.features.so.presentation.listview

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.data.domain.models.UserRole
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import kotlinx.coroutines.launch

class ListViewStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository,
    private val sessionManager: SessionManager,
): ViewModel() {
    var orientations by mutableStateOf<List<StrategicOrientation>>(
        emptyList()
    )
        private set

    var canCreate by mutableStateOf(false)
        private set

    init {
        loadOrientations()
    }

    private fun loadOrientations() {

        viewModelScope.launch {

            orientations = repository.getOrientations()

//            val currentUserId = sessionManager.getUserId()

            canCreate = sessionManager.getUserRole() == UserRole.LEADER
        }
    }
}