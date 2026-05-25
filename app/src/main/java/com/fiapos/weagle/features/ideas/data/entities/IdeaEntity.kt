package com.fiapos.weagle.features.ideas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas")
data class IdeaEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val description: String,

    val type: String,

    val status: String,

    val createdBy: String,

    val createdAt: Long = System.currentTimeMillis(),

    val votes: Int = 0
)