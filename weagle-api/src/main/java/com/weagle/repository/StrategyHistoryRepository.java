package com.weagle.repository;

import com.weagle.entity.StrategyHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StrategyHistoryRepository extends MongoRepository<StrategyHistory, String> {
    List<StrategyHistory> findByStrategyIdOrderByRecordedAtDesc(String strategyId);
}
