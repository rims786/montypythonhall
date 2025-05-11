package com.example.treasuresim.controller;

import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;
import com.example.treasuresim.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the SimulationController class.
 * This class uses Spring's MockMvc to test the controller's endpoints.
 */
@WebMvcTest(SimulationController.class)
@AutoConfigureMockMvc(addFilters = false) // Disables security filters for testing
class SimulationControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc instance for simulating HTTP requests

    @MockBean
    private SimulationService simulationService; // Mocked SimulationService dependency

    /**
     * Sets up mock behavior for the SimulationService before each test.
     */
    @BeforeEach
    void setUp() {
        // Mock behavior for the SWITCH strategy
        when(simulationService.runSimulation(anyInt(), eq(SimulationStrategy.SWITCH)))
                .thenReturn(new SimulationResult(100, 60, new BigDecimal("0.6000")));

        // Mock behavior for the STAY strategy
        when(simulationService.runSimulation(anyInt(), eq(SimulationStrategy.STAY)))
                .thenReturn(new SimulationResult(100, 33, new BigDecimal("0.3300")));
    }

    /**
     * Tests the /api/simulations/run endpoint with the SWITCH strategy.
     * Expects a 200 OK response.
     */
    @Test
    void shouldRunSimulationWithSwitchStrategy() throws Exception {
        mockMvc.perform(get("/api/simulations/run")
                        .param("runs", "100") // Number of runs
                        .param("strategy", "SWITCH")) // Strategy: SWITCH
                .andExpect(status().isOk()); // Expect HTTP 200 OK
    }

    /**
     * Tests the /api/simulations/run endpoint with the STAY strategy.
     * Expects a 200 OK response.
     */
    @Test
    void shouldRunSimulationWithStayStrategy() throws Exception {
        mockMvc.perform(get("/api/simulations/run")
                        .param("runs", "100") // Number of runs
                        .param("strategy", "STAY")) // Strategy: STAY
                .andExpect(status().isOk()); // Expect HTTP 200 OK
    }

    /**
     * Tests the /api/simulations/run endpoint with an invalid number of runs.
     * Expects a 400 Bad Request response.
     */
    @Test
    void shouldReturnBadRequestForInvalidRuns() throws Exception {
        mockMvc.perform(get("/api/simulations/run")
                        .param("runs", "0") // Invalid number of runs
                        .param("strategy", "SWITCH")) // Strategy: SWITCH
                .andExpect(status().isBadRequest()); // Expect HTTP 400 Bad Request
    }
}
