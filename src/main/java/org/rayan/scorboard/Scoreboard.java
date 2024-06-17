package org.rayan.scorboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class Scoreboard {
    private List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }
    public void startNewMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }
    public List<Match> getMatches() {
        return matches;
    }
}
