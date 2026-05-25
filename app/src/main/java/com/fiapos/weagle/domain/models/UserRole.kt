package com.fiapos.weagle.domain.models

enum class UserRole (
    val label: String
) {
    OPERATOR(label = "Operador"),
    MANAGER(label = "Gestor"),
    LEADER(label = "Líder")
}