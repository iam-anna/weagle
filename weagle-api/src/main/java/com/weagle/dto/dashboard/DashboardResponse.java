package com.weagle.dto.dashboard;

import java.util.Map;

public record DashboardResponse(
        long totalProjects,
        long plannedProjects,
        long inProgressProjects,
        long completedProjects,
        long cancelledProjects,

        double totalInvestment,
        double totalReturn,
        double roi,

        double averageProductionGain,
        double totalCostReduction,

        Map<String, Long> projectsByStrategy
) {
}
