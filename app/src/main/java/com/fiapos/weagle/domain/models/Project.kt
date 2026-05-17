package com.fiapos.weagle.domain.models

import java.time.LocalDate

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val investment: Float,
    val ideaList: List<Idea> = mutableListOf()
)