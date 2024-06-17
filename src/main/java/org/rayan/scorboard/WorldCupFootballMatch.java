package org.rayan.scorboard;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class WorldCupFootballMatch implements Comparable<WorldCupFootballMatch> {

    public WorldCupFootballMatch( Team homeTeam, Team awayTeam ) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchStartTime = LocalDateTime.now();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    private final Team homeTeam;
    private final Team awayTeam;

    private final LocalDateTime matchStartTime;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof WorldCupFootballMatch worldCupFootballMatch ) ) return false;
        return Objects.equals( homeTeam.getName(), worldCupFootballMatch.homeTeam.getName() )
                && Objects.equals( awayTeam.getName(), worldCupFootballMatch.awayTeam.getName() );
    }

    @Override
    public int hashCode() {
        int result = homeTeam.hashCode();
        result = 31 * result + awayTeam.hashCode();
        return result;
    }

    @Override
    public int compareTo( WorldCupFootballMatch o ) {
        int thisTotalScore = this.homeTeam.getScore() + this.awayTeam.getScore();
        int otherTotalScore = o.getHomeTeam().getScore() + o.getAwayTeam().getScore();
        if (thisTotalScore != otherTotalScore) {
            return Integer.compare(thisTotalScore, otherTotalScore);
        } else {
            return this.matchStartTime.compareTo(o.matchStartTime);
        }
    }
}
