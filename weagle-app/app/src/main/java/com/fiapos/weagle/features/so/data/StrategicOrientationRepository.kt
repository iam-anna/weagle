package com.fiapos.weagle.features.so.data

import com.fiapos.weagle.features.so.data.dao.StrategicOrientationDao
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import com.fiapos.weagle.features.so.data.entities.StrategicOrientationEntity
import com.fiapos.weagle.features.so.data.mappers.toStrategicOrientation
import com.fiapos.weagle.data.remote.StrategyRequest
import com.fiapos.weagle.data.remote.WeagleApi
import java.time.LocalDate

class StrategicOrientationRepository(
    private val dao: StrategicOrientationDao,
    private val api: WeagleApi? = null
) {

    suspend fun createOrientation(
        title: String,
        description: String,
        category: StrategicOrientationCategory,
        status: StrategicOrientationStatus,
        createdBy: String,
    ) {

        if (api != null) {
            api.createStrategy(StrategyRequest(title, description, status.value))
            return
        }

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

        if (api != null) {
            return api.getStrategies().map { it.toDomain() }
        }

        return dao.getAll()
            .map {
                it.toStrategicOrientation()
            }
    }

    suspend fun getOrientationById(id: Int): StrategicOrientation? {
        if (api != null) {
            return runCatching { api.getStrategy(id.toString()).toDomain() }.getOrNull()
        }
        return dao.getById(id)?.toStrategicOrientation()
    }

    suspend fun updateOrientation(
        orientation: StrategicOrientation
    ) {
        if (api != null) {
            api.updateStrategy(
                orientation.id,
                StrategyRequest(orientation.title, orientation.description, orientation.isActive.value)
            )
            return
        }
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

    private fun com.fiapos.weagle.data.remote.StrategyResponse.toDomain() = StrategicOrientation(
        id = id,
        title = name,
        description = description,
        category = StrategicOrientationCategory.INNOVATION,
        isActive = StrategicOrientationStatus.fromBoolean(active),
        createdAt = createdAt?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrDefault(LocalDate.now()) }
            ?: LocalDate.now(),
        createdBy = "backend"
    )
}