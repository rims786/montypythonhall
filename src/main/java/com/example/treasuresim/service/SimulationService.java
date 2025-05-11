package com.example.treasuresim.service;

import com.example.treasuresim.model.SimulationResult;
import com.example.treasuresim.model.SimulationStrategy;

/**
 * Simulation service interface for running Monty Hall simulations.
 */
public interface SimulationService {
    SimulationResult runSimulation(int runs, SimulationStrategy strategy);
}
