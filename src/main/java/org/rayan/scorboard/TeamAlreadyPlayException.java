package org.rayan.scorboard;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class TeamAlreadyPlayException extends RuntimeException {
    public TeamAlreadyPlayException( String message ) {
        super( message );
    }
}
