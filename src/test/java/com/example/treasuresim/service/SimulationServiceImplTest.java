package com.example.treasuresim.service;

import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SimulationServiceImpl class.
 * This class verifies the behavior of the Monty Hall simulation logic
 * implemented in the SimulationServiceImpl class.
 */
class SimulationServiceImplTest {

    private SimulationService simulationService;

    /**
     * Initializes the SimulationService instance before each test.
     */
    @BeforeEach
    void setUp() {
        simulationService = new SimulationServiceImpl();
    }

    /**
     * Tests the simulation logic when using the SWITCH strategy.
     * Verifies that the success rate is greater than 60% for 10,000 runs.
     */
    @Test
    void testRunSimulationWithSwitch() {
        SimulationResult result = simulationService.runSimulation(10000, SimulationStrategy.SWITCH);
        assertNotNull(result); // Ensure the result is not null
        assertEquals(10000, result.runs()); // Verify the number of runs
        assertTrue(result.successCount() > 6000, "Expected more than 60% success when switching");
    }

    /**
     * Tests the simulation logic when using the STAY strategy.
     * Verifies that the success rate is less than 40% for 10,000 runs.
     */
    @Test
    void testRunSimulationWithoutSwitch() {
        SimulationResult result = simulationService.runSimulation(10000, SimulationStrategy.STAY);
        assertNotNull(result); // Ensure the result is not null
        assertEquals(10000, result.runs()); // Verify the number of runs
        assertTrue(result.successCount() < 4000, "Expected less than 40% success when not switching");
    }

    /**
     * Tests the simulation logic with a single run.
     * Verifies that the success count is either 0 or 1.
     */
    @Test
    void testRunSimulationWithOneRun() {
        SimulationResult result = simulationService.runSimulation(1, SimulationStrategy.SWITCH);
        assertNotNull(result); // Ensure the result is not null
        assertEquals(1, result.runs()); // Verify the number of runs
        assertTrue(result.successCount() == 0 || result.successCount() == 1); // Verify success count
    }

    /**
     * Tests that the simulation throws an IllegalArgumentException
     * when the number of runs is invalid (e.g., 0).
     */
    @Test
    void testRunSimulationThrowsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> simulationService.runSimulation(0, SimulationStrategy.SWITCH));
    }
}
