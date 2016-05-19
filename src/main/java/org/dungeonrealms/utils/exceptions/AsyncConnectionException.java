package org.dungeonrealms.utils.exceptions;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class AsyncConnectionException extends Exception {

    /**
     * @param message The message.
     */
    public AsyncConnectionException(String message) {
        super(message);
    }

    /**
     * @param message The message.
     * @param cause   The throwable cause.
     */
    public AsyncConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
