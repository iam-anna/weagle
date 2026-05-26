package com.fiapos.weagle.features.projects.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fiapos.weagle.features.projects.data.entities.ProjectEntity
import com.fiapos.weagle.features.projects.data.relations.ProjectWithIdeas

@Dao
interface ProjectDao {

    @Insert
    suspend fun insert(project: ProjectEntity): Long

    @Query("""
        SELECT * FROM projects ORDER BY createdAt DESC
    """)
    suspend fun getAll(): List<ProjectEntity>

    @Transaction
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getById(
        projectId: Int
    ): ProjectWithIdeas

    @Update
    suspend fun update(project: ProjectEntity)

    @Delete
    suspend fun delete(project: ProjectEntity)
}