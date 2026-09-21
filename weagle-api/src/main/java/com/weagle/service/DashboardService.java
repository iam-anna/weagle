package com.weagle.service;

import com.weagle.dto.dashboard.DashboardResponse;
import com.weagle.entity.Project;
import com.weagle.repository.IdeaRepository;
import com.weagle.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final IdeaRepository ideaRepository;

    public DashboardService(ProjectRepository projectRepository, IdeaRepository ideaRepository) {
        this.projectRepository = projectRepository;
        this.ideaRepository = ideaRepository;
    }

    public DashboardResponse summarize(String strategyId) {
        List<Project> projects = projectRepository.findAll().stream()
                .filter(project -> strategyId == null || strategyId.equals(project.getStrategyId()))
                .toList();

        long totalProjects = projects.size();
        long completedProjects = projects.stream().filter(project -> project.getProgress() == 100).count();
        long plannedProjects = projects.stream().filter(project -> project.getProgress() == 0).count();
        long inProgressProjects = totalProjects - completedProjects - plannedProjects;
        double totalInvestment = projects.stream().mapToDouble(Project::getInvestment).sum();
        double totalFinancialReturn = projects.stream().mapToDouble(Project::getFinancialReturn).sum();
        double roiPercentage = totalInvestment == 0
                ? 0
                : ((totalFinancialReturn - totalInvestment) / totalInvestment) * 100;

        Map<String, Long> projectsByStatus = new LinkedHashMap<>();
        projectsByStatus.put("PLANNED", plannedProjects);
        projectsByStatus.put("IN_PROGRESS", inProgressProjects);
        projectsByStatus.put("COMPLETED", completedProjects);

        Map<String, Long> projectsByStrategy = new LinkedHashMap<>();
        projects.stream()
                .map(Project::getStrategyId)
                .filter(strategy -> strategy != null && !strategy.isBlank())
                .forEach(strategy -> projectsByStrategy.merge(strategy, 1L, Long::sum));

        return new DashboardResponse(
                totalProjects,
                completedProjects,
                inProgressProjects,
                plannedProjects,
                ideaRepository.count(),
                ideaRepository.countByApprovedTrue(),
                ideaRepository.countByHighPriorityTrue(),
                totalInvestment,
                totalFinancialReturn,
                roiPercentage,
                totalProjects == 0 ? 0 : projects.stream().mapToInt(Project::getProgress).average().orElse(0),
                projects.stream().mapToDouble(Project::getProductivityIncrease).sum(),
                projects.stream().mapToDouble(Project::getCostReduction).sum(),
                projectsByStatus,
                projectsByStrategy
        );
    }
}
