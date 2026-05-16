package com.fiapos.weagle.features.so.presentation.listview

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.domain.models.OrientationCategory
import com.fiapos.weagle.domain.models.StrategicOrientation
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository

class ListViewStrategicOrientationViewModel(
    private val repository: StrategicOrientationRepository
): ViewModel() {
    var orientations by mutableStateOf<List<StrategicOrientation>>(
        emptyList()
    )
        private set

    init {
        loadOrientations()
    }

    private fun loadOrientations() {
//        orientations = repository.getOrientationsList()
        orientations = listOf(
            StrategicOrientation(
                id = "1",
                title = "Estimular automação de processos",
                description = "Inovação",
                category = OrientationCategory.INNOVATION,
                createdBy = "John Doe",
            ),
            StrategicOrientation(
                id = "2",
                title = "Diminuir desperdícios operacionais",
                description = "Redução de Custos",
                category = OrientationCategory.COST_REDUCTION,
                createdBy = "John Doe",
            ),
            StrategicOrientation(
                id = "3",
                title = "Aumentar eficiência do atendimento",
                description = "Produtividade",
                category = OrientationCategory.PRODUCTIVITY,
                createdBy = "John Doe",
            ),
            StrategicOrientation(
                id = "4",
                title = "Reduzir consumo de papel",
                description = "Sustentabilidade",
                category = OrientationCategory.SUSTAINABILITY,
                createdBy = "John Doe",
            ),
            StrategicOrientation(
                id = "5",
                title = "Migrar sistemas legados",
                description = "Tecnologia",
                category = OrientationCategory.TECHNOLOGY,
                createdBy = "John Doe",
            ),
        )
    }
}