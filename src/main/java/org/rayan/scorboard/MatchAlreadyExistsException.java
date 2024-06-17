package org.rayan.scorboard;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class MatchAlreadyExistsException extends RuntimeException {
    public MatchAlreadyExistsException( String message ) {
        super( message );
    }
}