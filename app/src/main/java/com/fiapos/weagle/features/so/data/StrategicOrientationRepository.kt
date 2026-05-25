package com.fiapos.weagle.features.so.data

import com.fiapos.weagle.features.so.data.dao.StrategicOrientationDao
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.mappers.toStrategicOrientation

class StrategicOrientationRepository(
    private val dao: StrategicOrientationDao
) {

    suspend fun getOrientations(): List<StrategicOrientation> {

        return dao.getAll()
            .map {
                it.toStrategicOrientation()
            }
    }

    suspend fun getOrientationById(id: Int): StrategicOrientation? {
        return dao.getById(id)?.toStrategicOrientation()
    }
}