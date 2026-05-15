package com.fiapos.weagle.features.so.data

import com.fiapos.weagle.domain.models.StrategicOrientation

class StrategicOrientationRepository {
    private val strategicOrientations = mutableListOf<StrategicOrientation>()

    fun getOrientation(id: String): StrategicOrientation? {
        return strategicOrientations.find {
            it.id == id
        }
    }

    fun getOrientationsList(): List<StrategicOrientation> {
        return strategicOrientations
    }
}