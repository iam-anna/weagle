package com.weagle.service;

import com.weagle.dto.dashboard.DashboardResponse;
import com.weagle.entity.Project;
import com.weagle.entity.ProjectStatus;
import com.weagle.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;

    public DashboardService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public DashboardResponse getDashboard(){

        List<Project> projects = projectRepository.findAll();

        long totalProjects = projects.size();

        long plannedProjects = countByStatus(
                projects,
                ProjectStatus.PLANNED
        );

        long inProgressProjects = countByStatus(
                projects,
                ProjectStatus.IN_PROGRESS
        );

        long completedProjects = countByStatus(
                projects,
                ProjectStatus.COMPLETED
        );

        long cancelledProjects = countByStatus(
                projects,
                ProjectStatus.CANCELLED
        );

        double totalInvestment = projects.stream()
                .mapToDouble(Project::getInvestment)
                .sum();

        double totalReturn = projects.stream()
                .mapToDouble(Project::getReturnValue)
                .sum();

        double roi = calculateRoi(
                totalInvestment,
                totalReturn
        );

        double averageProductivityGain = projects.stream()
                .mapToDouble(Project::getProductivityGain)
                .average()
                .orElse(0);

        double totalCostReduction = projects.stream()
                .mapToDouble(Project::getCostReduction)
                .sum();

        Map<String, Long> projectsByStrategy =
                projects.stream()
                        .filter(project ->
                                project.getStrategyId() != null
                        )
                        .collect(Collectors.groupingBy(
                                Project::getStrategyId,
                                Collectors.counting()
                        ));

        return new DashboardResponse(
                totalProjects,
                plannedProjects,
                inProgressProjects,
                completedProjects,
                cancelledProjects,
                totalInvestment,
                totalReturn,
                roi,
                averageProductivityGain,
                totalCostReduction,
                projectsByStrategy
        );
    }

    private long countByStatus(
            List<Project> projects,
            ProjectStatus status
    ){
        return projects.stream()
                .filter(project -> project.getStatus() == status)
                .count();
    }

    private double calculateRoi(
            double investment,
            double returnValue
    ) {

        if(investment == 0) {
            return 0;
        }

        return ((returnValue - investment)/ investment) * 100;
    }
}
