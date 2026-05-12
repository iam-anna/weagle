package com.fiapos.weagle.auth.login

import com.fiapos.weagle.domain.models.User
import com.fiapos.weagle.domain.models.UserRole

class AuthRepository {
    fun login(
        email: String,
        password: String
    ): User? {

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

            else -> null
        }
    }
}