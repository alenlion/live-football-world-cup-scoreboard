package org.rayan.scorboard;

import org.rayan.scorboard.exceptions.ScoreNotValidException;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class Team {
    private final String name;

    public Team( String name ) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        if ( score < 0 ) {
            throw new ScoreNotValidException(
                   this.getName() +  " team score can not be negative values." );
        }
        this.score = score;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Team team ) ) return false;

        return name.equals( team.name );
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
