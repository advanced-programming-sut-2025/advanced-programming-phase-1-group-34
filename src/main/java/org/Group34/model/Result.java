package org.Group34.model;

/**
 * Represents the result of an operation with a success flag and a message
 *
 * @param success true if the operation was successful, false otherwise
 * @param message a descriptive message about the result
 */

public record Result(boolean success, String message) {

}
