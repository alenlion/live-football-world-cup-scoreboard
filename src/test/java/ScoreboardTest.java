import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rayan.scorboard.*;
import org.rayan.scorboard.exceptions.MatchAlreadyExistsException;
import org.rayan.scorboard.exceptions.ScoreNotValidException;
import org.rayan.scorboard.exceptions.TeamAlreadyPlayException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */
@DisplayName( "Test scoreboard operations. " )
class ScoreboardTest {
    @DisplayName( value = "start new match" )
    @Test
    void testCreateNewMatch_whenNewMatchStart() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        assertFalse( scoreboard.getMatches().isEmpty() );
        assertEquals( "Mexico", scoreboard.getMatches().get( 0 ).getHomeTeam().getName() );
        assertEquals( 0, scoreboard.getMatches().get( 0 ).getHomeTeam().getScore() );
        assertEquals( "Canada", scoreboard.getMatches().get( 0 ).getAwayTeam().getName() );
        assertEquals( 0, scoreboard.getMatches().get( 0 ).getAwayTeam().getScore() );
    }

    @DisplayName( "start duplicate match" )
    @Test
    void testStartNewMatch_whenSameTeamExistOnScoreboard() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Canada" );
        assertThrows( MatchAlreadyExistsException.class, () -> scoreboard.startNewMatch( home1, away1 ) );
    }

    @DisplayName( "start match with team already playing." )
    @Test
    void testStartNewMatch_whenOneOfTeamAlreadyOnScoreboard() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Spain" );
        assertThrows( TeamAlreadyPlayException.class, () -> scoreboard.startNewMatch( home1, away1 ) );
        Team home2 = new Team( "Spain" );
        Team away2 = new Team( "Canada" );
        assertThrows( TeamAlreadyPlayException.class, () -> scoreboard.startNewMatch( home2, away2 ) );
    }

    @DisplayName( "update match." )
    @Test
    void testUpdateMatchScore() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        home.setScore( 0 );
        away.setScore( 5 );
        scoreboard.updateMatch( home, away );
        Match match = scoreboard.findMatchByHomeAndAwayTeam( home, away );
        assertEquals( 0, match.getHomeTeam().getScore() );
        assertEquals( 5, match.getAwayTeam().getScore() );
    }

    @DisplayName( "update match with negative score." )
    @Test
    void testUpdateMatch_whenScoreNegative() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        assertThrows( ScoreNotValidException.class, () -> home.setScore( -1 ) );
        assertThrows( ScoreNotValidException.class, () -> away.setScore( -1 ) );
    }

    @DisplayName( "finish match" )
    @Test
    void testFinishMatch() {
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        scoreboard.finishMatch( home, away );
        assertTrue( scoreboard.getMatches().isEmpty() );
    }

    @DisplayName( "summary of matches in progress ordered by their total score" )
    @Test
    void testSummeryOfMatchOrderByTotalScore() {
        Scoreboard scoreboard = new Scoreboard();
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Canada" );
        scoreboard.startNewMatch( home1, away1 );
        home1.setScore( 0 );
        away1.setScore( 5 );
        scoreboard.updateMatch( home1, away1 );

        Team home2 = new Team( "Spain" );
        Team away2 = new Team( "Brazil" );
        scoreboard.startNewMatch( home2, away2 );
        home2.setScore( 10 );
        away2.setScore( 2 );
        scoreboard.updateMatch( home2, away2 );

        Team home3 = new Team( "Germany" );
        Team away3 = new Team( "France" );
        scoreboard.startNewMatch( home3, away3 );
        home3.setScore( 2 );
        away3.setScore( 2 );
        scoreboard.updateMatch( home3, away3 );

        Team home4 = new Team( "Uruguay" );
        Team away4 = new Team( "Italy" );
        scoreboard.startNewMatch( home4, away4 );
        home4.setScore( 6 );
        away4.setScore( 6 );
        scoreboard.updateMatch( home4, away4 );

        Team home5 = new Team( "Argentina" );
        Team away5 = new Team( "Australia" );
        scoreboard.startNewMatch( home5, away5 );
        home5.setScore( 3 );
        away5.setScore( 1 );
        scoreboard.updateMatch( home5, away5 );

        List<Match> summaryOfMatches = scoreboard.getSummaryOfMatches();
        assertFalse( summaryOfMatches.isEmpty() );
        assertEquals( new Match( home4, away4 ), summaryOfMatches.get( 0 ) );
        assertEquals( new Match( home2, away2 ), summaryOfMatches.get( 1 ) );
        assertEquals( new Match( home1, away1 ), summaryOfMatches.get( 2 ) );
        assertEquals( new Match( home5, away5 ), summaryOfMatches.get( 3 ) );
        assertEquals( new Match( home3, away3 ), summaryOfMatches.get( 4 ) );
    }
}
