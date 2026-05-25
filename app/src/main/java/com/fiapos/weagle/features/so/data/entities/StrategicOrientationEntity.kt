package com.fiapos.weagle.features.so.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fiapos.weagle.features.auth.data.entities.UserEntity

@Entity(
    tableName = "strategic_orientation",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["createdBy"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("createdBy")]
)
data class StrategicOrientationEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val description: String,

    val category: String,

    val isActive: Boolean = false,

    val createdAt: Long = System.currentTimeMillis(),

    val isEdited: Boolean = false,

    val createdBy: String,
)