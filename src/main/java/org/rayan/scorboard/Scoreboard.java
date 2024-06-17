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
        Match match = new Match( homeTeam, awayTeam );
        if(matches.contains(match)) {
            throw new MatchAlreadyExistsException(
                   "( " + homeTeam.getName() + "-" + awayTeam.getName() + ") this match already on progress."
            );
        }

        matches.add( new Match( homeTeam, awayTeam ) );
    }

    public List<Match> getMatches() {
        return matches;
    }
}
