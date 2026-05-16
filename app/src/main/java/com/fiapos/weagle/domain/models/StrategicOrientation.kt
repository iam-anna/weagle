package com.fiapos.weagle.domain.models

data class StrategicOrientation (
    val id: String = "",
    val title: String,
    val description: String,
    val category: OrientationCategory,
    val isActive: Boolean = false
)