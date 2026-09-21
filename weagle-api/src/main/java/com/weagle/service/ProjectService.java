package com.weagle.service;

import com.weagle.entity.Project;
import com.weagle.entity.ProjectStatus;
import com.weagle.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {

        return projectRepository.findAll();
    }

    public Project findById(String id) {

        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Projeto não encontrado")
                );
    }

    public Project create(Project project) {

        project.setId(null);

        if (project.getStatus() == null) {
            project.setStatus(ProjectStatus.PLANNED);
        }

        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    public Project update(String id, Project updatedProject) {

        Project project = this.findById(id);

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setStrategyId(updatedProject.getStrategyId());
        project.setIdeaId(updatedProject.getIdeaId());
        project.setStatus(updatedProject.getStatus());
        project.setProgress(updatedProject.getProgress());
        project.setInvestment(updatedProject.getInvestment());
        project.setReturnValue(updatedProject.getReturnValue());
        project.setProductivityGain(updatedProject.getProductivityGain());
        project.setCostReduction(updatedProject.getCostReduction());
        project.setResults(updatedProject.getResults());
        project.setInvestment(updatedProject.getInvestment());
        project.setFinancialReturn(updatedProject.getFinancialReturn());
        project.setProductivityIncrease(updatedProject.getProductivityIncrease());
        project.setCostReduction(updatedProject.getCostReduction());
        project.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    public void delete(String id) {

        Project project = this.findById(id);

        projectRepository.delete(project);
    }

    public Project updateProgress(
            String id,
            int progress
    ) {

        if(progress < 0 || progress > 100) {
            throw new IllegalArgumentException(
                    "O progresso deve estar entre 0 e 100"
            );
        }

        Project project = this.findById(id);

        project.setProgress(progress);
        project.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    public Project updateResults(
            String id,
            String results
    ) {

        Project project = this.findById(id);

        project.setResults(results);
        project.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }
}
