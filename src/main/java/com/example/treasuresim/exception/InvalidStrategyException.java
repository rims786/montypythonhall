package com.example.treasuresim.exception;

/**
 * Exception thrown when an invalid strategy is encountered in the treasure simulation.
 * This runtime exception indicates that the provided strategy does not meet the required criteria
 * or is not valid for the current simulation context.
 */
public class InvalidStrategyException extends RuntimeException {

    /**
     * Constructs a new InvalidStrategyException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     *               This message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public InvalidStrategyException(String message) {
        super(message);
    }
}
