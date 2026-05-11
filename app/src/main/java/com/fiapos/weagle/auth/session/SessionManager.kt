package com.fiapos.weagle.auth.session

import com.fiapos.weagle.domain.models.User

class SessionManager {
    private var currentUser: User? = null

    fun saveUser(user: User) {
        currentUser = user
    }

    fun getUser(): User? {
        return currentUser
    }

    fun logout() {
        currentUser = null
    }
}