package com.fiapos.weagle.features.so.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository

class EditStrategicOrientationViewModelFactory(
    private val strategicOrientationRepository: StrategicOrientationRepository,
    private val orientationId: String,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditStrategicOrientationViewModel::class.java)) {
            return EditStrategicOrientationViewModel(
                strategicOrientationRepository,
                orientationId
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}