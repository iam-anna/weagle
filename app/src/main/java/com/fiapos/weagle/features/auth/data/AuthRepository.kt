package com.fiapos.weagle.features.auth.data

import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.auth.data.mappers.toUser
import com.fiapos.weagle.features.auth.data.domain.models.User

class AuthRepository(
    private val userDao: UserDao
) {

    suspend fun createdUser(
        user: UserEntity
    ) {

        userDao.insert(user)
    }

    suspend fun login(
        email: String,
        password: String
    ): User? {

        return userDao.login(
            email,
            password
        )?.toUser()
    }
}