package org.rayan.scorboard;

import org.rayan.scorboard.exceptions.MatchAlreadyExistsException;
import org.rayan.scorboard.exceptions.MatchNotInScoreboardException;
import org.rayan.scorboard.exceptions.TeamAlreadyPlayException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class WorldCupLiveScoreboard {
    private final List<WorldCupFootballMatch> worldCupFootballMatches;

    public WorldCupLiveScoreboard() {
        this.worldCupFootballMatches = new ArrayList<>();
    }

    private void checkTeamValidToPlay( Team homeTeam, Team awayTeam ) {
        WorldCupFootballMatch worldCupFootballMatch = new WorldCupFootballMatch( homeTeam, awayTeam );
        if ( worldCupFootballMatches.contains( worldCupFootballMatch ) ) {
            throw new MatchAlreadyExistsException(
                    "( " + homeTeam.getName() + "-" + awayTeam.getName() + ") this match already on progress."
            );
        }
        for ( WorldCupFootballMatch m : worldCupFootballMatches ) {
            if ( m.getHomeTeam().equals( homeTeam ) ||
                    m.getHomeTeam().equals( awayTeam ) ) {
                throw new TeamAlreadyPlayException(
                        m.getHomeTeam().getName() + "team can not play in mor than one match at the same time."
                );
            }
            if ( m.getAwayTeam().equals( homeTeam ) ||
                    m.getAwayTeam().equals( awayTeam ) ) {
                throw new TeamAlreadyPlayException(
                        m.getAwayTeam().getName() + "team can not play in mor than one match at the same time."
                );
            }
        }
    }

    public void startNewMatch( Team homeTeam, Team awayTeam ) {
        checkTeamValidToPlay( homeTeam, awayTeam );

        worldCupFootballMatches.add( new WorldCupFootballMatch( homeTeam, awayTeam ) );
    }

    public List<WorldCupFootballMatch> getMatches() {
        return worldCupFootballMatches;
    }

    public void updateMatch( Team homeTeam, Team awayTeam ) {
        WorldCupFootballMatch worldCupFootballMatch = findMatchByHomeAndAwayTeam( homeTeam, awayTeam );
        worldCupFootballMatch.getHomeTeam().setScore( homeTeam.getScore() );
        worldCupFootballMatch.getAwayTeam().setScore( awayTeam.getScore() );
    }

    public void finishMatch( Team homeTeam, Team awayTeam ) {
        worldCupFootballMatches.removeIf( worldCupFootballMatch -> worldCupFootballMatch.getHomeTeam().equals( homeTeam ) && worldCupFootballMatch.getAwayTeam().equals( awayTeam ) );
    }

    public WorldCupFootballMatch findMatchByHomeAndAwayTeam( Team homeTeam, Team awayTeam ) {
        for ( WorldCupFootballMatch worldCupFootballMatch : worldCupFootballMatches ) {
            if ( worldCupFootballMatch.getHomeTeam().equals( homeTeam ) && worldCupFootballMatch.getAwayTeam().equals( awayTeam ) ) {
                return worldCupFootballMatch;
            }
        }
        throw new MatchNotInScoreboardException( "( " + homeTeam.getName() + "-" + awayTeam.getName() + ") match not found." );
    }

    public List<WorldCupFootballMatch> getSummaryOfMatches() {
        List<WorldCupFootballMatch> summaryOfWorldCupFootballMatches = new ArrayList<>( worldCupFootballMatches.stream().sorted().toList() );
        Collections.reverse( summaryOfWorldCupFootballMatches );
        return summaryOfWorldCupFootballMatches;
    }
}
