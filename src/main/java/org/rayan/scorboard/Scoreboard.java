package org.rayan.scorboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class Scoreboard {
    private final List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    public void startNewMatch( Team homeTeam, Team awayTeam ) {
        matches.add( new Match( homeTeam, awayTeam ) );
    }

    public List<Match> getMatches() {
        return matches;
    }
}
