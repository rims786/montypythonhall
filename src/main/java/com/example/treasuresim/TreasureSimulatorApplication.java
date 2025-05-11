package com.example.treasuresim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Treasure Simulation Application.
 * This class bootstraps the Spring Boot application and starts the embedded server.
 */
@SpringBootApplication
public class TreasureSimulatorApplication {

	/**
	 * Main method to launch the Treasure Simulation Application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(TreasureSimulatorApplication.class, args);
	}

}
