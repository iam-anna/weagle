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

    fun saveUserProfile(name: String, email: String) {
        prefs.edit()
            .putString("user_name", name)
            .putString("user_email", email)
            .apply()
    }

    fun getUserName(): String? = prefs.getString("user_name", null)

    fun getUserEmail(): String? = prefs.getString("user_email", null)

    fun saveToken(token: String) {
        prefs.edit()
            .putString("token", token)
            .apply()
    }

    fun getToken(): String? = prefs.getString("token", null)

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
        return getUserId() != null && getToken() != null
    }


    fun logout() {
        prefs.edit()
            .clear()
            .apply()
    }
}