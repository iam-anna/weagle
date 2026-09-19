package com.fiapos.weagle.features.auth.data.domain.models

data class User (
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
)