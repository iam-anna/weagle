package com.fiapos.weagle.features.projects.data

import com.fiapos.weagle.features.projects.data.dao.ProjectDao
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.mappers.toProject

class ProjectRepository(
    private val dao: ProjectDao
) {
    private val projects = mutableListOf<Project>()

    fun createProject(project: Project): Project {
        projects.add(project)
        return project
    }

    suspend fun getProjects(): List<Project> {

        return dao.getAll()
            .map {
                it.toProject()
            }
    }
}