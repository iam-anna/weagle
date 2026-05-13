package com.fiapos.weagle.domain.models

data class Idea(
    val id: String = "",
    val title: String,
    val description: String,
    val type: IdeaType,
    val status: IdeaStatus = IdeaStatus.PENDING,
    val createdBy: String
)