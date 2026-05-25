package com.fiapos.weagle.features.auth.session

import android.content.Context
import com.fiapos.weagle.domain.models.User

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