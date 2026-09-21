package com.weagle.controller;

import com.weagle.dto.strategy.StrategyResponse;
import com.weagle.dto.strategy.StrategyResquest;
import com.weagle.entity.Strategy;
import com.weagle.entity.StrategyHistory;
import com.weagle.service.StrategyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/strategies")
public class StrategyController {

    private final StrategyService strategyService;

    public StrategyController(StrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @GetMapping
    public List<StrategyResponse> findAll() {
        return strategyService.findAll()
                .stream()
                .map(StrategyResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public StrategyResponse findById(@PathVariable String id) {
        return StrategyResponse.fromEntity(
                strategyService.findById(id)
        );
    }

        @GetMapping("/{id}/history")
        public List<StrategyHistory> history(@PathVariable String id) {
                return strategyService.history(id);
        }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StrategyResponse create(
            @Valid @RequestBody StrategyResquest request
    ) {

        Strategy strategy = Strategy.builder()
                .name(request.name())
                .description(request.description())
                .category(request.category())
                .campaign(request.campaign())
                .active(request.active())
                .build();

        return StrategyResponse.fromEntity(
                strategyService.create(strategy)
        );
    }

    @PutMapping("/{id}")
    public StrategyResponse update(
            @PathVariable String id,
            @Valid @RequestBody StrategyResquest request
    ) {

        Strategy strategy = Strategy.builder()
                .name(request.name())
                .description(request.description())
                .category(request.category())
                .campaign(request.campaign())
                .active(request.active())
                .build();

        return StrategyResponse.fromEntity(
                strategyService.update(id, strategy)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        strategyService.delete(id);
    }
}
