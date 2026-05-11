package com.fiapos.weagle.domain.models

import com.fiapos.weagle.domain.permissions.UserRole

data class User (
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole
)