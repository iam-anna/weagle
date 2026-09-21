package com.fiapos.weagle.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.TopNavigation

@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navigationController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 64.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopNavigation(
            title = "Dashboard",
            onBackPressed = { navigationController.popBackStack() }
        )

        when (val state = viewModel.state) {
            DashboardState.Loading -> CircularProgressIndicator()
            is DashboardState.Error -> {
                Text(state.message, color = MaterialTheme.colorScheme.error)
                CustomButton(text = "Tentar novamente", onClick = viewModel::load)
            }
            is DashboardState.Success -> {
                val dashboard = state.dashboard
                Metric("Projetos", dashboard.totalProjects.toString())
                Metric("Projetos concluídos", dashboard.completedProjects.toString())
                Metric("Ideias aprovadas", dashboard.approvedIdeas.toString())
                Metric("Investimento total", "R$ %.2f".format(dashboard.totalInvestment))
                Metric("Retorno financeiro", "R$ %.2f".format(dashboard.totalFinancialReturn))
                Metric("ROI", "%.2f%%".format(dashboard.roiPercentage))
                Metric("Progresso médio", "%.2f%%".format(dashboard.averageProgress))
                Metric("Aumento de produtividade", "%.2f%%".format(dashboard.productivityIncrease))
                Metric("Redução de custos", "%.2f%%".format(dashboard.costReduction))
            }
        }
    }
}

@Composable
private fun Metric(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Text(value, style = MaterialTheme.typography.headlineSmall)
    }
}
