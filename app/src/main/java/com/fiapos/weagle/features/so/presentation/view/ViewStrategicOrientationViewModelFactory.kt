package com.fiapos.weagle.features.so.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository

class ViewStrategicOrientationViewModelFactory(
    private val strategicOrientationRepository: StrategicOrientationRepository,
    private val orientationId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewStrategicOrientationViewModel::class.java)) {
            return ViewStrategicOrientationViewModel(
                strategicOrientationRepository,
                orientationId
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}