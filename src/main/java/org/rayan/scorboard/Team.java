package org.rayan.scorboard;

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
        this.score = score;
    }
    
}
