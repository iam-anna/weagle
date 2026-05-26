package com.fiapos.weagle.features.ideas.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fiapos.weagle.features.ideas.data.entities.IdeaEntity

@Dao
interface IdeaDao {

    @Insert
    suspend fun insert(idea: IdeaEntity)


    @Query("""
       SELECT * FROM ideas ORDER BY createdAt DESC 
    """)
    suspend fun getAll(): List<IdeaEntity>

    @Query("""
        SELECT * FROM ideas
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun getById(id: Int): IdeaEntity?

    @Update
    suspend fun update(idea: IdeaEntity)

    @Delete
    suspend fun delete(idea: IdeaEntity)
}