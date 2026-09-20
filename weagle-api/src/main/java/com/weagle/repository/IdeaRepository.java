package com.weagle.repository;

import com.weagle.entity.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IdeaRepository extends MongoRepository<Idea, String> {

    List<Idea> findByCreatedBy(String createdBy);
}
