package com.example.treasuresim.controller;

import com.example.treasuresim.exception.InvalidStrategyException;
import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;
import com.example.treasuresim.service.SimulationService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controller class for handling simulation-related API requests.
 * Provides endpoints for running treasure simulation based on user-defined parameters.
 */
@RestController
@RequestMapping("/api/simulations")
@Validated
public class SimulationController {

    // Logger instance for logging simulation requests and activities
    private static final Logger log = LoggerFactory.getLogger(SimulationController.class);

    // Service layer dependency for handling simulation logic
    private final SimulationService simulationService;

    /**
     * Constructor for injecting the SimulationService dependency.
     *
     * @param simulationService The service responsible for executing simulation logic.
     */
    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    /**
     * Endpoint to run the treasure simulation.
     * Accepts the number of runs and the strategy to be used in the simulation.
     *
     * @param runs     Number of simulation runs (minimum 1). Defaults to 1000 if not provided.
     * @param strategy Simulation strategy: SWITCH or STAY. Defaults to SWITCH if not provided.
     * @return SimulationResult containing the success rate and statistics of the simulation.
     * @throws InvalidStrategyException if the provided strategy is invalid.
     */
    @GetMapping("/run")
    public SimulationResult runSimulation(
            @RequestParam(defaultValue = "1000") @Min(1)
            @Parameter(description = "Number of simulation runs (minimum 1)")
            int runs,

            @RequestParam(defaultValue = "SWITCH")
            @Parameter(
                    name = "strategy",
                    description = "Strategy used in simulation: SWITCH or STAY",
                    schema = @Schema(type = "string", allowableValues = {"SWITCH", "STAY"})
            )
            String strategy) {

        // Parse the strategy string into an enum value
        SimulationStrategy simStrategy;

        try {
            simStrategy = SimulationStrategy.valueOf(strategy.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Throw custom exception if the strategy is invalid
            throw new InvalidStrategyException("Invalid strategy: " + strategy + ". Allowed values are SWITCH or STAY.");
        }

        // Log the simulation request details
        log.info("Received simulation request: runs={}, strategy={}", runs, simStrategy);

        // Delegate the simulation execution to the service layer
        return simulationService.runSimulation(runs, simStrategy);
    }
}
