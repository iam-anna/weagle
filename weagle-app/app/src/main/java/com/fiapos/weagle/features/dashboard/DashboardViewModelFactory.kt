package com.fiapos.weagle.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.data.remote.WeagleApi

class DashboardViewModelFactory(private val api: WeagleApi) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
