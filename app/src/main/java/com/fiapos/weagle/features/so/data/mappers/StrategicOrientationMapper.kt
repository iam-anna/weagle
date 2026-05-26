package com.fiapos.weagle.features.so.data.mappers

import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import com.fiapos.weagle.features.so.data.entities.StrategicOrientationEntity
import java.time.Instant
import java.time.ZoneId

fun StrategicOrientationEntity.toStrategicOrientation(): StrategicOrientation {
    return StrategicOrientation(
        id = id.toString(),
        title = title,
        description = description,
        category = StrategicOrientationCategory.valueOf(category),
        isActive = StrategicOrientationStatus.fromBoolean(isActive),
        createdAt = Instant
            .ofEpochMilli(createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        isEdited = isEdited,
        createdBy = createdBy
    )
}