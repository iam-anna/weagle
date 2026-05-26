package com.fiapos.weagle.features.auth.data

import com.fiapos.weagle.domain.models.User
import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.mappers.toUser

class UserRepository(
    private val dao: UserDao
) {
    suspend fun getUserById(id: Int): User? {
        return dao.getById(id)?.toUser()
    }
}