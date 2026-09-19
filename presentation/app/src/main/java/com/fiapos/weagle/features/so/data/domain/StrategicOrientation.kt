package com.fiapos.weagle.features.so.data.domain

import java.time.LocalDate

data class StrategicOrientation (
    val id: String,
    val title: String,
    val description: String,
    val category: StrategicOrientationCategory,
    val isActive: StrategicOrientationStatus = StrategicOrientationStatus.INACTIVE,
    val createdAt: LocalDate = LocalDate.now(),
    val isEdited: Boolean = false,
    val createdBy: String,
)