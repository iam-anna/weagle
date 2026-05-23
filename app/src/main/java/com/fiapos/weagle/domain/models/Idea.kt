package com.fiapos.weagle.domain.models

import java.time.LocalDate

data class Idea(
    val id: String = "",
    val title: String,
    val description: String,
    val type: IdeaType,
    val status: IdeaStatus = IdeaStatus.PENDING,
    val createdBy: String,
    val createdAt: LocalDate?,
    val isEdited: Boolean = false,
    val votes: Int = 0,
    val isSelectable: Boolean = false,
    val isSelected: Boolean = false
)