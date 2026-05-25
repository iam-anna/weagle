package com.fiapos.weagle.features.auth.data

import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.auth.data.mappers.toUser
import com.fiapos.weagle.domain.models.User

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

//
//        return when (email) {
//            "operator@test.com" -> User(
//                id = "1",
//                name = "Operator",
//                email = email,
//                role = UserRole.OPERATOR
//            )
//
//            "manager@test.com" -> User(
//                id = "2",
//                name = "Manager",
//                email = email,
//                role = UserRole.MANAGER
//            )
//
//            "leader@test.com" -> User(
//                id = "3",
//                name = "Leader",
//                email = email,
//                role = UserRole.LEADER
//            )
//
//            else -> null
//        }
    }
}