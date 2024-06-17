package org.rayan.scorboard.exceptions;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class MatchNotInScoreboardException extends RuntimeException {
    public MatchNotInScoreboardException( String message ) {
        super( message );
    }
}
