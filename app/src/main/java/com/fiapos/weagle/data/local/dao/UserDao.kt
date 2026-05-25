package com.fiapos.weagle.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fiapos.weagle.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(
        user: UserEntity
    )

    @Query("""
        SELECT * FROM users
        WHERE email = :email
        AND password = :password
        LIMIT 1
    """)
    suspend fun login(
        email: String,
        password: String
    ): UserEntity?

    @Query("""
        SELECT * FROM users
        WHERE email = :email
        LIMIT 1
    """)
    suspend fun getByEmail(
        email: String
    ): UserEntity?

    @Query(
        "SELECT * FROM users"
    )
    suspend fun getAll():
        List<UserEntity>
}