package org.rayan.scorboard;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class Match {
    private String homeTeam;
    private String awayTeam;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam( String homeTeam ) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam( String awayTeam ) {
        this.awayTeam = awayTeam;
    }

    public Match( String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
