package com.fiapos.weagle.features.projects.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fiapos.weagle.features.auth.data.entities.UserEntity
import java.time.LocalDate

@Entity(
    tableName = "projects",
    foreignKeys = [
        ForeignKey(entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownedBy"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index("ownedBy")]
)
data class ProjectEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val description: String,

    val status: String,

    val startDate: Long = System.currentTimeMillis(),

    val endDate: Long = System.currentTimeMillis(),

    val investment: Float,

    val ownedBy: String,

    val createdAt: Long = System.currentTimeMillis()
)