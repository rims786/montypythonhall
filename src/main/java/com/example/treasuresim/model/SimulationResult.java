package com.example.treasuresim.model;

import java.math.BigDecimal;

/**
 * Represents the result of a simulation run.
 * @param runs number of total simulations
 * @param successCount number of successes (player wins)
 * @param successRate success percentage as BigDecimal
 */
public record SimulationResult(int runs, int successCount, BigDecimal successRate) {}
