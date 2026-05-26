package com.fiapos.weagle.features.ideas.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fiapos.weagle.features.auth.data.entities.UserEntity

@Entity(
    tableName = "ideas",
    foreignKeys = [
        ForeignKey(entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["createdBy"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("createdBy")]
)
data class IdeaEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val description: String,

    val type: String,

    val status: String,

    val createdBy: String,

    val createdAt: Long = System.currentTimeMillis(),

    val isEdited: Boolean = false,

    val votes: Int = 0
)