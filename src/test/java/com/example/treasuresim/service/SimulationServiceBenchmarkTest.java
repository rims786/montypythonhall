package com.example.treasuresim.service;

import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Benchmark tests for the SimulationService implementation.
 * This class measures the performance of the Monty Hall simulation
 * with a large number of runs for both SWITCH and STAY strategies.
 */
class SimulationServiceBenchmarkTest {

    // Instance of the SimulationService to be tested
    private final SimulationService simulationService = new SimulationServiceImpl();

    /**
     * Benchmarks the performance of the simulation when using the SWITCH strategy.
     * Measures the time taken to execute a large number of simulation runs.
     */
    @Test
    void benchmarkRunSimulationWithSwitch() {
        int runs = 1000000; // Test with a large number of runs

        // Start timing the benchmark
        long startTime = System.nanoTime();

        // Run the simulation with the SWITCH strategy
        SimulationResult result = simulationService.runSimulation(runs, SimulationStrategy.SWITCH);

        // End timing the benchmark
        long endTime = System.nanoTime();

        // Calculate the time taken in nanoseconds
        long duration = endTime - startTime;

        // Convert nanoseconds to milliseconds for easier readability
        double durationInMs = duration / 1_000_000.0;

        // Print the benchmark result
        System.out.println("Benchmark with switching: Time taken for " + runs + " runs: " + durationInMs + " ms");

        // Assert that the result is not null
        assertNotNull(result);
    }

    /**
     * Benchmarks the performance of the simulation when using the STAY strategy.
     * Measures the time taken to execute a large number of simulation runs.
     */
    @Test
    void benchmarkRunSimulationWithoutSwitch() {
        int runs = 1000000; // Test with a large number of runs

        // Start timing the benchmark
        long startTime = System.nanoTime();

        // Run the simulation with the STAY strategy
        SimulationResult result = simulationService.runSimulation(runs, SimulationStrategy.STAY);

        // End timing the benchmark
        long endTime = System.nanoTime();

        // Calculate the time taken in nanoseconds
        long duration = endTime - startTime;

        // Convert nanoseconds to milliseconds for easier readability
        double durationInMs = duration / 1_000_000.0;

        // Print the benchmark result
        System.out.println("Benchmark without switching: Time taken for " + runs + " runs: " + durationInMs + " ms");

        // Assert that the result is not null
        assertNotNull(result);
    }
}
