package com.fiapos.weagle.data.mappers

import com.fiapos.weagle.data.local.entities.UserEntity
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