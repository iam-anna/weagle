package com.fiapos.weagle.features.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.data.remote.DashboardResponse
import com.fiapos.weagle.data.remote.WeagleApi
import kotlinx.coroutines.launch

sealed interface DashboardState {
    data object Loading : DashboardState
    data class Success(val dashboard: DashboardResponse) : DashboardState
    data class Error(val message: String) : DashboardState
}

class DashboardViewModel(private val api: WeagleApi) : ViewModel() {
    var state by mutableStateOf<DashboardState>(DashboardState.Loading)
        private set

    init {
        load()
    }

    fun load() {
        state = DashboardState.Loading
        viewModelScope.launch {
            state = runCatching { api.getDashboard() }
                .fold(
                    onSuccess = { DashboardState.Success(it) },
                    onFailure = { DashboardState.Error(it.message ?: "Não foi possível carregar o dashboard") }
                )
        }
    }
}
