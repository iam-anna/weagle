package com.fiapos.weagle.features.auth.data.mappers

import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.domain.models.User
import com.fiapos.weagle.domain.models.UserRole

fun UserEntity.toUser(): User {
    return User(

        id = id.toString(),

        name = name,

        email = email,

        role = UserRole.valueOf(role)
    )
}