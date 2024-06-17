package org.rayan.scorboard;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class Match {

    public Match( Team homeTeam, Team awayTeam ) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam( Team homeTeam ) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam( Team awayTeam ) {
        this.awayTeam = awayTeam;
    }

    private Team homeTeam;
    private Team awayTeam;

}
