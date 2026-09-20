package com.fiapos.weagle.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiapos.weagle.features.auth.data.dao.UserDao
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import com.fiapos.weagle.features.ideas.data.dao.IdeaDao
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity
import com.fiapos.weagle.features.projects.data.dao.ProjectDao
import com.fiapos.weagle.features.projects.data.entities.ProjectEntity
import com.fiapos.weagle.features.so.data.dao.StrategicOrientationDao
import com.fiapos.weagle.features.so.data.entities.StrategicOrientationEntity

@Database(
    entities = [
        UserEntity::class,
        IdeaEntity::class,
        ProjectEntity::class,
        StrategicOrientationEntity::class,
    ],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun ideaDao(): IdeaDao

    abstract fun projectDao(): ProjectDao

    abstract fun strategicOrientationDao(): StrategicOrientationDao
}