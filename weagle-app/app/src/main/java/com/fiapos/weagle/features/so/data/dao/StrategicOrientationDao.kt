package com.fiapos.weagle.features.so.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fiapos.weagle.features.so.data.entities.StrategicOrientationEntity

@Dao
interface StrategicOrientationDao {

    @Insert
    suspend fun insert(orientation: StrategicOrientationEntity)

    @Query("""
        SELECT * FROM strategic_orientation
        ORDER BY createdAt DESC
    """)
    suspend fun getAll(): List<StrategicOrientationEntity>

    @Query("""
        SELECT * FROM strategic_orientation
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun getById(id: Int): StrategicOrientationEntity?

    @Update
    suspend fun update(orientation: StrategicOrientationEntity)

    @Delete
    suspend fun delete(orientation: StrategicOrientationEntity)
}