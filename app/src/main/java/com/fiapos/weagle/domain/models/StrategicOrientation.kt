package com.fiapos.weagle.domain.models

import java.time.LocalDate

data class StrategicOrientation (
    val id: String = "",
    val title: String,
    val description: String,
    val category: OrientationCategory,
    val isActive: Boolean = false,
    val createdAt: LocalDate = LocalDate.now(),
    val isEdited: Boolean = false,
    val createdBy: String,
)