package com.fiapos.weagle.features.auth.session

import android.content.Context
import com.fiapos.weagle.features.auth.data.domain.models.User
import com.fiapos.weagle.features.auth.data.domain.models.UserRole

class SessionManager(
    private val context: Context
) {
    private val prefs = context.getSharedPreferences(
        "session",
        Context.MODE_PRIVATE
    )

    fun saveUserId(userId: String) {
        prefs.edit()
            .putString(
                "user_id",
                userId
            )
            .apply()
    }

    fun saveUserRole(role: UserRole) {
        prefs.edit()
            .putString(
                "user_role",
                role.name
            )
            .apply()
    }

    fun getUserRole(): UserRole? {
        return UserRole.valueOf(
            prefs.getString(
                "user_role",
                null
            ) ?: ""
        )
    }

    fun getUserId(): String? {
        return prefs.getString(
            "user_id",
            null
        )
    }

    fun isLoggedIn(): Boolean {
        return getUserId() != null
    }


    fun logout() {
        prefs.edit()
            .clear()
            .apply()
    }
}