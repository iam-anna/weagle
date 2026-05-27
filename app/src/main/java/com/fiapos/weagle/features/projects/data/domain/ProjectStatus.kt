package com.fiapos.weagle.features.projects.data.domain

enum class ProjectStatus(
    val label: String
) {
    ACTIVE(label = "Ativo"),
    INACTIVE(label = "Inativo")
}