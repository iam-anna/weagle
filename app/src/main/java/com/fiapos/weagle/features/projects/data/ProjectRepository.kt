package com.fiapos.weagle.features.projects.data

import androidx.compose.runtime.mutableStateOf
import com.fiapos.weagle.features.ideas.data.dao.IdeaDao
import com.fiapos.weagle.features.projects.data.dao.ProjectDao
import com.fiapos.weagle.features.projects.data.domain.Project
import com.fiapos.weagle.features.projects.data.domain.ProjectStatus
import com.fiapos.weagle.features.projects.data.mappers.toProject
import com.fiapos.weagle.features.projects.data.entities.ProjectEntity
import com.fiapos.weagle.features.projects.data.relations.toProject
import java.time.LocalDate
import java.time.ZoneId

class ProjectRepository(
    private val projectDao: ProjectDao,
    private val ideaDao: IdeaDao
) {
    private val projects = mutableStateOf<List<Project>>(
        emptyList()
    )


    suspend fun createProject(
        name: String,
        description: String,
        status: ProjectStatus,
        startDate: LocalDate,
        endDate: LocalDate,
        investment: Float,
        ownedBy: String,
        ideaIds: List<Int>
    ) {

        val projectId = projectDao.insert(
            ProjectEntity(
                name = name,
                description = description,

                status = status.name,

                startDate = startDate
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli(),

                endDate = endDate
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli(),

                investment = investment,
                ownedBy = ownedBy
            )
        ).toInt()

        ideaIds.forEach { ideaId ->
            ideaDao.attachToProject(
                ideaId,
                projectId
            )
        }
    }


    suspend fun getProjects(): List<Project> {
        return projectDao.getAll()
            .map {
                it.toProject()
            }
    }

    suspend fun getProject(projectId: Int): Project {
        return projectDao
            .getById(projectId)
            .toProject()
    }
}