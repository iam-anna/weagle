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
import com.fiapos.weagle.data.remote.ProjectRequest
import com.fiapos.weagle.data.remote.WeagleApi

class ProjectRepository(
    private val projectDao: ProjectDao,
    private val ideaDao: IdeaDao,
    private val api: WeagleApi? = null
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

        if (api != null) {
            api.createProject(
                ProjectRequest(
                    name = name,
                    description = description,
                    status = if (status == ProjectStatus.ACTIVE) "IN_PROGRESS" else "PLANNED",
                    progress = if (status == ProjectStatus.ACTIVE) 1 else 0,
                    investment = investment.toDouble()
                )
            )
            return
        }

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
        if (api != null) {
            return api.getProjects().map { it.toDomain() }
        }
        return projectDao.getAll()
            .map {
                it.toProject()
            }
    }

    suspend fun getProject(projectId: String): Project {
        if (api != null) {
            return api.getProject(projectId).toDomain()
        }
        return projectId.toIntOrNull()?.let { projectDao.getById(it).toProject() }
            ?: throw IllegalArgumentException("Projeto inválido")
    }

    suspend fun getProject(projectId: Int): Project = getProject(projectId.toString())

    private fun com.fiapos.weagle.data.remote.ProjectResponse.toDomain() = Project(
        id = id,
        name = name,
        description = description,
        status = if (status == "COMPLETED" || status == "IN_PROGRESS") ProjectStatus.ACTIVE else ProjectStatus.INACTIVE,
        startDate = createdAt?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrDefault(LocalDate.now()) }
            ?: LocalDate.now(),
        endDate = updatedAt?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrDefault(LocalDate.now()) }
            ?: LocalDate.now(),
        investment = investment.toFloat(),
        ideaList = emptyList(),
        ownedBy = "backend",
        createdAt = createdAt?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrDefault(LocalDate.now()) }
            ?: LocalDate.now()
    )
}