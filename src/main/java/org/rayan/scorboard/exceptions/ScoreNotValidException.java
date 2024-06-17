package org.rayan.scorboard.exceptions;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class ScoreNotValidException extends RuntimeException {
    public ScoreNotValidException( String message ) {
        super( message );
    }
}
