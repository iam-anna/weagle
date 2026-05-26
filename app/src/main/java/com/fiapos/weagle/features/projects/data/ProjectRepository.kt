package com.fiapos.weagle.features.projects.data

import com.fiapos.weagle.features.projects.data.dao.ProjectDao
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.mappers.toProject
import java.time.LocalDate

class ProjectRepository(
    private val dao: ProjectDao
) {
    private val projects = mutableListOf<Project>(
// TODO: Deletar esses mocks
//        Project(
//            id = "1",
//            name = "aaa",
//            description = "dsf",
//            status = ProjectStatus.ACTIVE,
//            startDate = LocalDate.now(),
//            endDate = LocalDate.now(),
//            investment = 2000.3f,
//            ideaList = listOf(),
//            ownedBy = "FULANO DE tal",
//            createdAt = LocalDate.now()
//        ),
//        Project(
//            id = "1",
//            name = "aaa",
//            description = "dsf",
//            status = ProjectStatus.ACTIVE,
//            startDate = LocalDate.now(),
//            endDate = LocalDate.now(),
//            investment = 2000.3f,
//            ideaList = listOf(),
//            ownedBy = "FULANO DE tal",
//            createdAt = LocalDate.now()
//        ),
//        Project(
//            id = "1",
//            name = "aaa",
//            description = "dsf",
//            status = ProjectStatus.ACTIVE,
//            startDate = LocalDate.now(),
//            endDate = LocalDate.now(),
//            investment = 2000.3f,
//            ideaList = listOf(),
//            ownedBy = "FULANO DE tal",
//            createdAt = LocalDate.now()
//        )
    )


    fun createProject(project: Project): Project {
        projects.add(project)
        return project
    }

    suspend fun getProjects(): List<Project> {
        return projects
// TODO: Remover mock e integrar com banco
//        return dao.getAll()
//            .map {
//                it.toProject()
//            }
    }
}