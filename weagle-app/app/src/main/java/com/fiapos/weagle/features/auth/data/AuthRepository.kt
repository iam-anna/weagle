package com.fiapos.weagle.features.auth.data

import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.auth.data.domain.models.User
import com.fiapos.weagle.features.auth.data.domain.models.UserRole
import com.fiapos.weagle.data.remote.LoginRequest
import com.fiapos.weagle.data.remote.WeagleApi

class AuthRepository(
    private val userDao: UserDao,
    private val api: WeagleApi? = null
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

        if (api != null) {
            return try {
                val response = api.login(LoginRequest(email, password))
                User(
                    id = response.id,
                    name = response.name,
                    email = response.email,
                    role = UserRole.valueOf(response.role),
                    token = response.token
                )
            } catch (_: Exception) {
                null
            }
        }

        return null
    }
}