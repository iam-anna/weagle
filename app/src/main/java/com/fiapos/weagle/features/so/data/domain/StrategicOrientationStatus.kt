package com.fiapos.weagle.features.so.data.domain

enum class StrategicOrientationStatus(
    val label: String,
    val value: Boolean
) {
    ACTIVE(label = "Ativo", value = true),
    INACTIVE(label = "Inativo", value = false)
}