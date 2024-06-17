package org.rayan.scorboard;

import java.util.List;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public interface Scoreboard {
    void startNewMatch( Team homeTeam, Team awayTeam );
    List<WorldCupFootballMatch> getMatches();
    void updateMatch( Team homeTeam, Team awayTeam );
    void finishMatch( Team homeTeam, Team awayTeam );
    WorldCupFootballMatch findMatchByHomeAndAwayTeam( Team homeTeam, Team awayTeam );
    List<WorldCupFootballMatch> getSummaryOfMatches();
}
