package com.fiapos.weagle.features.projects.data

import com.fiapos.weagle.domain.models.Project

class ProjectRepository {
    private val projects = mutableListOf<Project>()

    fun createProject(project: Project): Project {
        projects.add(project)
        return project
    }
}