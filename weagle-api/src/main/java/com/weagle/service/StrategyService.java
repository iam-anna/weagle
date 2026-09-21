package com.weagle.service;

import com.weagle.entity.Strategy;
import com.weagle.repository.StrategyRepository;
import com.weagle.entity.StrategyHistory;
import com.weagle.repository.StrategyHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StrategyService {

    private final StrategyRepository strategyRepository;
    private final StrategyHistoryRepository historyRepository;

    public StrategyService(StrategyRepository strategyRepository, StrategyHistoryRepository historyRepository) {
        this.strategyRepository = strategyRepository;
        this.historyRepository = historyRepository;
    }

    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    public Strategy findById(String id) {
        return strategyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Estratégia não encontrada")
                );
    }

    public List<StrategyHistory> history(String strategyId) {
        findById(strategyId);
        return historyRepository.findByStrategyIdOrderByRecordedAtDesc(strategyId);
    }

    public Strategy create(Strategy strategy) {
        strategy.setId(null);
        strategy.setCreatedAt(LocalDateTime.now());

        Strategy saved = strategyRepository.save(strategy);
        record(saved, "CREATED");
        return saved;
    }

    public Strategy update(String id, Strategy updatedStrategy) {
        Strategy strategy = findById(id);

        strategy.setName(updatedStrategy.getName());
        strategy.setDescription(updatedStrategy.getDescription());
        strategy.setCategory(updatedStrategy.getCategory());
        strategy.setCampaign(updatedStrategy.getCampaign());
        strategy.setActive(updatedStrategy.isActive());

        Strategy saved = strategyRepository.save(strategy);
        record(saved, "UPDATED");
        return saved;
    }

    public void delete(String id) {
        Strategy strategy = findById(id);
        record(strategy, "DELETED");
        strategyRepository.delete(strategy);
    }

    private void record(Strategy strategy, String action) {
        historyRepository.save(StrategyHistory.builder()
                .strategyId(strategy.getId())
                .name(strategy.getName())
                .description(strategy.getDescription())
                .category(strategy.getCategory())
                .campaign(strategy.getCampaign())
                .action(action)
                .recordedAt(LocalDateTime.now())
                .build());
    }
}
