package com.weagle.repository;

import com.weagle.entity.Strategy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StrategyRepository extends MongoRepository<Strategy, String> {
}
