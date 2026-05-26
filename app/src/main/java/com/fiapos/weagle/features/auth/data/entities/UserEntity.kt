package com.fiapos.weagle.features.auth.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val email: String,

    val password: String,

    val role: String,

    val createdAt: Long = System.currentTimeMillis(),

    val isActive: Boolean
)