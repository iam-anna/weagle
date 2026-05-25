package com.fiapos.weagle.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.ideas.data.dao.IdeaDao
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity

@Database(
    entities = [
        UserEntity::class,
        IdeaEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun ideaDao(): IdeaDao
}