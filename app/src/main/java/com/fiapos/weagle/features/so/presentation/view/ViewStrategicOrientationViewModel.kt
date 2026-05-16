package com.fiapos.weagle.features.so.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.domain.models.OrientationCategory
import com.fiapos.weagle.domain.models.StrategicOrientation
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository

class ViewStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository,
    private val orientationId: String
): ViewModel() {

    var orientation by mutableStateOf<StrategicOrientation?>(
        null
    )
        private set

    init {
        loadOrientation()
    }

    private fun loadOrientation() {
//        orientation = repository.getOrientation(orientationId)
        orientation = StrategicOrientation(
            id = "1",
            title = "Automação de Processos Internos",
            description = "A empresa busca automatizar tarefas repetitivas para reduzir tempo operacional.",
            category = OrientationCategory.INNOVATION,
            isActive = true,
            createdBy = "John Doe",
        )
    }
}

