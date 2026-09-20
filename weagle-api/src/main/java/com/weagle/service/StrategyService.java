package com.weagle.service;

import com.weagle.entity.Strategy;
import com.weagle.repository.StrategyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StrategyService {

    private final StrategyRepository strategyRepository;

    public StrategyService(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
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

    public Strategy create(Strategy strategy) {
        strategy.setId(null);
        strategy.setCreatedAt(LocalDateTime.now());

        return strategyRepository.save(strategy);
    }

    public Strategy update(String id, Strategy updatedStrategy) {
        Strategy strategy = findById(id);

        strategy.setName(updatedStrategy.getName());
        strategy.setDescription(updatedStrategy.getDescription());
        strategy.setActive(updatedStrategy.isActive());

        return strategyRepository.save(strategy);
    }

    public void delete(String id) {
        Strategy strategy = findById(id);
        strategyRepository.delete(strategy);
    }
}
