package com.fiapos.weagle.features.projects.data.domain

import com.fiapos.weagle.features.ideas.domain.Idea
import java.time.LocalDate

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val status: ProjectStatus,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val investment: Float,
    val ideaList: List<Idea> = mutableListOf(),
    val ownedBy: String,
    val createdAt: LocalDate
)