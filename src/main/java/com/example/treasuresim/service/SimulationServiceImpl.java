package com.example.treasuresim.service;

import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Implementation of the SimulationService for executing Monty Hall simulations.
 * This service simulates the Monty Hall problem based on the provided number of runs
 * and the pirate's strategy (SWITCH or STAY).
 */
@Service
public class SimulationServiceImpl implements SimulationService {

    // Logger instance for logging simulation details
    private static final Logger log = LoggerFactory.getLogger(SimulationServiceImpl.class);

    /**
     * Runs the Monty Hall simulation for a specified number of runs and strategy.
     *
     * @param runs     The number of simulation runs (must be at least 1).
     * @param strategy The strategy to use in the simulation: SWITCH or STAY.
     * @return A SimulationResult object containing the total runs, success count, and success rate.
     * @throws IllegalArgumentException if the number of runs is less than 1.
     */
    @Override
    public SimulationResult runSimulation(int runs, SimulationStrategy strategy) {
        if (runs < 1) throw new IllegalArgumentException("Runs must be at least 1");

        // Determine if the pirate will switch their choice
        boolean switchChoice = strategy == SimulationStrategy.SWITCH;

        // Perform the simulation in parallel and count the number of successful outcomes
        int success = (int) IntStream.range(0, runs).parallel()
                .filter(i -> isWinningGame(switchChoice))
                .count();

        // Calculate the success rate as a BigDecimal with 4 decimal places
        BigDecimal rate = BigDecimal.valueOf(success)
                .divide(BigDecimal.valueOf(runs), 4, RoundingMode.HALF_UP);

        // Log the simulation results
        log.info("Simulated {} runs with strategy={}, Success: {}, Rate: {}",
                runs, strategy, success, rate);

        // Return the simulation results
        return new SimulationResult(runs, success, rate);
    }

    /**
     * Simulates a single game of the Monty Hall problem and determines if the pirate wins.
     *
     * @param switchChoice Whether the pirate switches their initial choice.
     * @return true if the pirate wins the game, false otherwise.
     */
    private boolean isWinningGame(boolean switchChoice) {
        // Randomly select the island with the treasure
        int treasure = ThreadLocalRandom.current().nextInt(3);

        // Randomly select the pirate's initial choice
        int pirateChoice = ThreadLocalRandom.current().nextInt(3);

        // Host opens a island that does not contain the treasure or the pirate's choice
        int hostOpens;
        do {
            hostOpens = ThreadLocalRandom.current().nextInt(3);
        } while (hostOpens == treasure || hostOpens == pirateChoice);

        // Determine the pirate's final choice based on their strategy
        int finalChoice = switchChoice ? (3 - pirateChoice - hostOpens) : pirateChoice;

        // Return true if the pirate's final choice matches the treasure island
        return finalChoice == treasure;
    }
}
