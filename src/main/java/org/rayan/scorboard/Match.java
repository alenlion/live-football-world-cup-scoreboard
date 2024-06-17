package org.rayan.scorboard;

import java.util.Objects;

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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Match match ) ) return false;
        return Objects.equals( homeTeam.getName(), match.homeTeam.getName() )
                && Objects.equals( awayTeam.getName(), match.awayTeam.getName() );
    }

    @Override
    public int hashCode() {
        int result = homeTeam.hashCode();
        result = 31 * result + awayTeam.hashCode();
        return result;
    }
}
