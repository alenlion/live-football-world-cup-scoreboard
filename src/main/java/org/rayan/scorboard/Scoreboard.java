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

    private void checkTeamValidToPlay( Team homeTeam, Team awayTeam ) {
        Match match = new Match( homeTeam, awayTeam );
        if ( matches.contains( match ) ) {
            throw new MatchAlreadyExistsException(
                    "( " + homeTeam.getName() + "-" + awayTeam.getName() + ") this match already on progress."
            );
        }
        for ( Match m : matches ) {
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

        matches.add( new Match( homeTeam, awayTeam ) );
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void updateMatch( Team homeTeam, Team awayTeam ) {
        Match match = findMatchByHomeAndAwayTeam( homeTeam, awayTeam );
        match.getHomeTeam().setScore( homeTeam.getScore() );
        match.getAwayTeam().setScore( awayTeam.getScore() );
    }

    public void finishMatch( Team homeTeam, Team awayTeam ) {
        matches.removeIf( match -> match.getHomeTeam().equals( homeTeam ) && match.getAwayTeam().equals( awayTeam ) );
    }

    public Match findMatchByHomeAndAwayTeam( Team homeTeam, Team awayTeam ) {
        for ( Match match : matches ) {
            if ( match.getHomeTeam().equals( homeTeam ) && match.getAwayTeam().equals( awayTeam ) ) {
                return match;
            }
        }
        throw new MatchNotInScoreboardException( "( " + homeTeam.getName() + "-" + awayTeam.getName() + ") match not found." );
    }
}
