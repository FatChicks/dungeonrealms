package org.dungeonrealms.utils.exceptions;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class AsyncConcurrentException extends Exception {

    /**
     * @param message The message.
     */
    public AsyncConcurrentException(String message) {
        super(message);
    }

    /**
     * @param message The message.
     * @param cause   The throwable cause.
     */
    public AsyncConcurrentException(String message, Throwable cause) {
        super(message, cause);
    }
}
