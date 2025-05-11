# Pirate's Treasure Island Simulator

## Overview
This Spring Boot application provides a REST API to simulate a probability puzzle inspired by the Monty Hall problem, but with a pirate treasure hunting theme. It allows users to run multiple iterations of the simulation to empirically demonstrate the probability outcomes of finding treasure when faced with Jack's suggestion to change islands.

## The Pirate's Treasure Problem
The scenario is as follows:

1. You discover a treasure map showing that an enormous treasure is buried on one of three islands
2. You initially choose to sail to the first island
3. On your journey, you meet Pirate Jack who:
    - Swears on his honor that the treasure is NOT on the second island
    - Suggests you change course to the third island
4. You must decide to either:
    - STAY with your original choice (first island)
    - SWITCH to the third island as suggested by Jack

## Features
- Simulate the Monty Hall problem with two strategies:
    - **Switch**: The contestant switches their choice after a door is revealed.
    - **Stay**: The contestant keeps their initial choice.
- Analyze success rates for each strategy.
- RESTful API for running simulations.
- OpenAPI documentation for API endpoints.
- Input validation and error handling.
- Benchmark tests for performance analysis.


## Technical Stack
- Java 24
- Spring Boot
- Spring Security
- OpenAPI (Swagger) for API documentation
- SLF4J for logging
- JUnit for testing

## Prerequisites
- Java 21 or higher
- Maven 3.8 or higher


## Getting Started

### Clone the Repository
```bash
git clone https://github.com/rims786/montypython-demo.git
cd montypython-demo
```

### Build the Application
Run the following command to build the application:
```bash
mvn clean install
```

### Run the Application
Start the application using:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.


## API Endpoints

### Run Simulation
**Endpoint**: `/api/simulations/run`  
**Method**: `GET`  
**Parameters**:
- `runs` (required): Number of simulation runs (e.g., 1000).
- `strategy` (required): Simulation strategy (`SWITCH` or `STAY`).



### API Testing Instructions

#### 1. **Browser UI**
You can test the API directly in your browser by navigating to the following URL:
```
http://localhost:8080/api/simulations/run?runs=1000&strategy=SWITCH
```
Replace `1000` and `SWITCH` with your desired number of runs and strategy (`SWITCH` or `STAY`).

---

#### 2. **Curl**
Use the following `curl` command to test the API from the terminal:
```bash
curl "http://localhost:8080/api/simulations/run?runs=1000&strategy=SWITCH"
```
Replace `1000` and `SWITCH` with your desired values.

---

#### 3. **Using `index.html`**
1. Open the application in your browser:
   ```
   http://localhost:8080/
   ```
2. Fill in the form with the number of runs and strategy.
3. Click the **Run Simulation** button to see the results displayed on the page.

---

#### 4. **Postman**
1. Open Postman and create a new request.
2. Set the request method to `GET`.
3. Enter the URL:
   ```
   http://localhost:8080/api/simulations/run?runs=1000&strategy=SWITCH
   ```
4. Click **Send** to view the response.

---

#### 5. **Swagger**
1. Open the Swagger UI in your browser:
   ```
   http://localhost:8080/swagger-ui.html
   ```
2. Locate the `/api/simulations/run` endpoint.
3. Click **Try it out**, enter the parameters (`runs` and `strategy`), and execute the request to see the response.


**Response**:
```json
{
  "runs": 1000,
  "successCount": 600,
  "successRate": 0.6
}
```


### OpenAPI Documentation
Access the API documentation at:  
`http://localhost:8080/swagger-ui.html`

## Testing
Run all tests using:
```bash
mvn test
```

### Test Coverage
- **Unit Tests**: Validate the logic of the Monty Hall simulation.
- **Integration Tests**: Ensure the application context loads and API endpoints work as expected.
- **Benchmark Tests**: Measure the performance of the simulation logic.


## Project Structure
```
treasure-sim/
├── .gitignore
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── treasuresim/
    │   │               ├── TreasureSimApplication.java
    │   │               ├── controller/
    │   │               │   └── SimulationController.java
    │   │               ├── service/
    │   │               │   ├── SimulationService.java
    │   │               │   └── SimulationServiceImpl.java
    │   │               ├── model/
    │   │               │   ├── SimulationResult.java
    │   │               │   └── SimulationStrategy.java
    │   │               ├── exception/
    │   │               │   ├── InvalidStrategyException.java
    │   │               │   └── GlobalExceptionHandler.java
    │   │               └── config/
    │   │                   ├── SecurityConfig.java
    │   │                   └── OpenApiConfig.java
    │   └── resources/
    │       ├── application.properties
    │       ├── application-dev.properties
    │       └── application-prod.properties
    └── test/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── treasuresim/
        │               ├── TreasureSimApplicationTests.java
        │               ├── controller/
        │               │   └── SimulationControllerIntegrationTest.java
        │               └── service/
        │                   └── SimulationServiceTest.java
        └── resources/
            └── application-test.properties

```


## Configuration
Edit the `application.properties` file in `src/main/resources` to configure the application.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Author
- **Rahim Uddin**
- **rimmy2008@gmail.com**

```
