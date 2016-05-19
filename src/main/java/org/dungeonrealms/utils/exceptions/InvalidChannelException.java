package org.dungeonrealms.utils.exceptions;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class InvalidChannelException extends Exception {

    /**
     * @param message The message.
     */
    public InvalidChannelException(String message) {
        super(message);
    }

    /**
     * @param message The message.
     * @param cause The cause/
     */
    public InvalidChannelException(String message, Throwable cause) {
        super(message, cause);
    }
}
