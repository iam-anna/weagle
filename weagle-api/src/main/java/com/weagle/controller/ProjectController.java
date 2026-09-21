package com.weagle.controller;

import com.weagle.dto.project.ProjectRequest;
import com.weagle.dto.project.ProjectResponse;
import com.weagle.entity.Project;
import com.weagle.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> findAll() {

        return projectService.findAll()
                .stream()
                .map(ProjectResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(
            @PathVariable String id
    ) {

        return ProjectResponse.fromEntity(
                projectService.findById(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(
            @Valid @RequestBody ProjectRequest request
    ) {
        Project project = Project.builder()
                .name(request.name())
                .description(request.description())
                .strategyId(request.strategyId())
                .ideaId(request.ideaId())
                .progress(request.progress())
                .results(request.results())
                .investment(request.investment())
                .financialReturn(request.financialReturn())
                .productivityIncrease(request.productivityIncrease())
                .costReduction(request.costReduction())
                .build();

        return ProjectResponse.fromEntity(
                projectService.create(project)
        );
    }

    @PutMapping("/{id}")
    public ProjectResponse update(
            @PathVariable String id,
            @Valid @RequestBody ProjectRequest request
    ) {

        Project project = Project.builder()
                .name(request.name())
                .description(request.description())
                .strategyId(request.strategyId())
                .ideaId(request.ideaId())
                .progress(request.progress())
                .investment(request.investments())
                .returnValue(request.returnValue())
                .productivityGain(request.productivityGain())
                .costReduction(request.costReduction())
                .results(request.results())
                .investment(request.investment())
                .financialReturn(request.financialReturn())
                .productivityIncrease(request.productivityIncrease())
                .costReduction(request.costReduction())
                .build();

        return ProjectResponse.fromEntity(
                projectService.update(id, project)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ) {

        projectService.delete(id);
    }

    @PatchMapping("/{id}/progress")
    public ProjectResponse updateProgress(
            @PathVariable String id,
            @RequestParam int progress
    ) {

        return ProjectResponse.fromEntity(
                projectService.updateProgress(
                        id,
                        progress
                )
        );
    }

    @PatchMapping("/{id}/results")
    public ProjectResponse updateResults(
            @PathVariable String id,
            @RequestBody String results
    ) {

        return ProjectResponse.fromEntity(
                projectService.updateResults(
                        id,
                        results
                )
        );
    }
}
