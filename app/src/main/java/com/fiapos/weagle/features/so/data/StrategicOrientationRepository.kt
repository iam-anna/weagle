package com.fiapos.weagle.features.so.data

import com.fiapos.weagle.features.so.data.dao.StrategicOrientationDao
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import com.fiapos.weagle.features.so.data.entities.StrategicOrientationEntity
import com.fiapos.weagle.features.so.data.mappers.toStrategicOrientation

class StrategicOrientationRepository(
    private val dao: StrategicOrientationDao
) {

    suspend fun createOrientation(
        title: String,
        description: String,
        category: StrategicOrientationCategory,
        status: StrategicOrientationStatus,
        createdBy: String,
    ) {

        dao.insert(
            StrategicOrientationEntity(
                title = title,
                description = description,
                category = category.name,
                isActive = status.value,
                createdBy = createdBy
            )
        )
    }

    suspend fun getOrientations(): List<StrategicOrientation> {

        return dao.getAll()
            .map {
                it.toStrategicOrientation()
            }
    }

    suspend fun getOrientationById(id: Int): StrategicOrientation? {
        return dao.getById(id)?.toStrategicOrientation()
    }

    suspend fun updateOrientation(
        orientation: StrategicOrientation
    ) {
        dao.update(
            StrategicOrientationEntity(
                id = orientation.id.toInt(),
                title = orientation.title,
                description = orientation.description,
                category = orientation.category.name,
                isActive = orientation.isActive.value,
                isEdited = true,
                createdBy = orientation.createdBy
            )
        )
    }
}