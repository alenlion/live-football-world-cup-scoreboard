import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rayan.scorboard.Team;
import org.rayan.scorboard.WorldCupFootballMatch;
import org.rayan.scorboard.WorldCupLiveScoreboard;
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
class WorldCupLiveScoreboardTest {
   private WorldCupLiveScoreboard worldCupLiveScoreboard;
    @BeforeEach
    void setUp() {
        worldCupLiveScoreboard = new WorldCupLiveScoreboard();
    }

    @DisplayName( value = "start new match" )
    @Test
    void testCreateNewMatch_whenNewMatchStart() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        assertFalse( worldCupLiveScoreboard.getMatches().isEmpty() );
        assertEquals( "Mexico", worldCupLiveScoreboard.getMatches().get( 0 ).getHomeTeam().getName() );
        assertEquals( 0, worldCupLiveScoreboard.getMatches().get( 0 ).getHomeTeam().getScore() );
        assertEquals( "Canada", worldCupLiveScoreboard.getMatches().get( 0 ).getAwayTeam().getName() );
        assertEquals( 0, worldCupLiveScoreboard.getMatches().get( 0 ).getAwayTeam().getScore() );
    }

    @DisplayName( "start duplicate match" )
    @Test
    void testStartNewMatch_whenSameTeamExistOnScoreboard() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Canada" );
        assertThrows( MatchAlreadyExistsException.class, () -> worldCupLiveScoreboard.startNewMatch( home1, away1 ) );
    }

    @DisplayName( "start match with team already playing." )
    @Test
    void testStartNewMatch_whenOneOfTeamAlreadyOnScoreboard() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Spain" );
        assertThrows( TeamAlreadyPlayException.class, () -> worldCupLiveScoreboard.startNewMatch( home1, away1 ) );
        Team home2 = new Team( "Spain" );
        Team away2 = new Team( "Canada" );
        assertThrows( TeamAlreadyPlayException.class, () -> worldCupLiveScoreboard.startNewMatch( home2, away2 ) );
    }

    @DisplayName( "update match." )
    @Test
    void testUpdateMatchScore() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        home.setScore( 0 );
        away.setScore( 5 );
        worldCupLiveScoreboard.updateMatch( home, away );
        WorldCupFootballMatch worldCupFootballMatch = worldCupLiveScoreboard.findMatchByHomeAndAwayTeam( home, away );
        assertEquals( 0, worldCupFootballMatch.getHomeTeam().getScore() );
        assertEquals( 5, worldCupFootballMatch.getAwayTeam().getScore() );
    }

    @DisplayName( "update match with negative score." )
    @Test
    void testUpdateMatch_whenScoreNegative() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        assertThrows( ScoreNotValidException.class, () -> home.setScore( -1 ) );
        assertThrows( ScoreNotValidException.class, () -> away.setScore( -1 ) );
    }

    @DisplayName( "finish match" )
    @Test
    void testFinishMatch() {
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home, away );
        worldCupLiveScoreboard.finishMatch( home, away );
        assertTrue( worldCupLiveScoreboard.getMatches().isEmpty() );
    }

    @DisplayName( "summary of matches in progress ordered by their total score" )
    @Test
    void testSummeryOfMatchOrderByTotalScore() {
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Canada" );
        worldCupLiveScoreboard.startNewMatch( home1, away1 );
        home1.setScore( 0 );
        away1.setScore( 5 );
        worldCupLiveScoreboard.updateMatch( home1, away1 );

        Team home2 = new Team( "Spain" );
        Team away2 = new Team( "Brazil" );
        worldCupLiveScoreboard.startNewMatch( home2, away2 );
        home2.setScore( 10 );
        away2.setScore( 2 );
        worldCupLiveScoreboard.updateMatch( home2, away2 );

        Team home3 = new Team( "Germany" );
        Team away3 = new Team( "France" );
        worldCupLiveScoreboard.startNewMatch( home3, away3 );
        home3.setScore( 2 );
        away3.setScore( 2 );
        worldCupLiveScoreboard.updateMatch( home3, away3 );

        Team home4 = new Team( "Uruguay" );
        Team away4 = new Team( "Italy" );
        worldCupLiveScoreboard.startNewMatch( home4, away4 );
        home4.setScore( 6 );
        away4.setScore( 6 );
        worldCupLiveScoreboard.updateMatch( home4, away4 );

        Team home5 = new Team( "Argentina" );
        Team away5 = new Team( "Australia" );
        worldCupLiveScoreboard.startNewMatch( home5, away5 );
        home5.setScore( 3 );
        away5.setScore( 1 );
        worldCupLiveScoreboard.updateMatch( home5, away5 );

        List<WorldCupFootballMatch> summaryOfWorldCupFootballMatches = worldCupLiveScoreboard.getSummaryOfMatches();
        assertFalse( summaryOfWorldCupFootballMatches.isEmpty() );
        assertEquals( new WorldCupFootballMatch( home4, away4 ), summaryOfWorldCupFootballMatches.get( 0 ) );
        assertEquals( new WorldCupFootballMatch( home2, away2 ), summaryOfWorldCupFootballMatches.get( 1 ) );
        assertEquals( new WorldCupFootballMatch( home1, away1 ), summaryOfWorldCupFootballMatches.get( 2 ) );
        assertEquals( new WorldCupFootballMatch( home5, away5 ), summaryOfWorldCupFootballMatches.get( 3 ) );
        assertEquals( new WorldCupFootballMatch( home3, away3 ), summaryOfWorldCupFootballMatches.get( 4 ) );
    }
}
