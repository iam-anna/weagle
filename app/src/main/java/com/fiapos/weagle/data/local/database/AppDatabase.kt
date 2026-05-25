package com.fiapos.weagle.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiapos.weagle.data.local.dao.UserDao
import com.fiapos.weagle.data.local.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao():
        UserDao
}