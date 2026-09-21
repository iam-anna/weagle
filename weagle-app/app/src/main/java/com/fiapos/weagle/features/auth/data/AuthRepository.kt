package com.fiapos.weagle.features.auth.data

import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.auth.data.mappers.toUser
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

        return when (email) {
            "operator@test.com" -> User(
                id = "1",
                name = "Operator",
                email = email,
                role = UserRole.OPERATOR
            )

            "manager@test.com" -> User(
                id = "2",
                name = "Manager",
                email = email,
                role = UserRole.MANAGER
            )

            "leader@test.com" -> User(
                id = "3",
                name = "Leader",
                email = email,
                role = UserRole.LEADER
            )


            //return userDao.login(
            //  email,
            //  password
            //  )?.toUser()

            else -> null
        }
    }
}