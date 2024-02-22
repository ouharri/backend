package com.ouharri.aftas.exceptions;

/**
 * Custom exception class for handling authentication failures.
 * This class extends RuntimeException and is used throughout the application
 * to indicate problems related to user authentication.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
public class AuthenticationFailedException extends RuntimeException {

    /**
     * Constructs a new AuthenticationFailedException with null as its detail message.
     */
    public AuthenticationFailedException() {
        super();
    }

    /**
     * Constructs a new AuthenticationFailedException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public AuthenticationFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthenticationFailedException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new AuthenticationFailedException with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public AuthenticationFailedException(Throwable cause) {
        super(cause);
    }
}
