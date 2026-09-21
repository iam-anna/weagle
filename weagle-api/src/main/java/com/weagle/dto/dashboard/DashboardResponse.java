package com.weagle.dto.dashboard;

import java.util.Map;

public record DashboardResponse(
        long totalProjects,
        long completedProjects,
        long inProgressProjects,
        long plannedProjects,
        long totalIdeas,
        long approvedIdeas,
        long highPriorityIdeas,
        double totalInvestment,
        double totalFinancialReturn,
        double roiPercentage,
        double averageProgress,
        double productivityIncrease,
        double costReduction,
        Map<String, Long> projectsByStatus,
        Map<String, Long> projectsByStrategy
) { }
