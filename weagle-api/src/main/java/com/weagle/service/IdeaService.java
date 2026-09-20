package com.weagle.service;

import com.weagle.entity.Idea;
import com.weagle.repository.IdeaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> findAll() {
        return ideaRepository.findAll();
    }

    public Idea findById(String id) {
        return ideaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ideia não encontrada")
                );
    }

    public Idea create(
            String title,
            String description,
            String createdBy
    ) {
        Idea idea = Idea.builder()
                .title(title)
                .description(description)
                .createdBy(createdBy)
                .approved(false)
                .highPriority(false)
                .createdAt(LocalDateTime.now())
                .build();
        return ideaRepository.save(idea);
    }

    public Idea update(
            String id,
            String title,
            String description,
            String currentUser
    ) {
        Idea idea = findById(id);

        if(!idea.getCreatedBy().equals(currentUser)) {
            throw new RuntimeException(
                    "Você só pode editar suas próprias ideias"
            );
        }

        idea.setTitle(title);
        idea.setDescription(description);

        return ideaRepository.save(idea);
    }

    public void delete(
            String id,
            String currentUser
    ) {
        Idea idea = findById(id);

        if(!idea.getCreatedBy().equals(currentUser)) {
            throw new RuntimeException(
                    "Você só pode excluir suas próprias ideias"
            );
        }

        ideaRepository.delete(idea);
    }

    public Idea approve(String id) {
        Idea idea = findById(id);

        idea.setApproved(true);

        return ideaRepository.save(idea);
    }

    public Idea setPriority(
            String id,
            boolean highPriority
    ) {
        Idea idea = findById(id);

        idea.setHighPriority(highPriority);

        return ideaRepository.save(idea);
    }
}
