package com.fiapos.weagle.features.so.presentation.listview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository

class ListViewStrategicOrientationViewModelFactory(
    private val strategicOrientationRepository: StrategicOrientationRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewStrategicOrientationViewModel::class.java)) {
            return ListViewStrategicOrientationViewModel(
                strategicOrientationRepository
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}