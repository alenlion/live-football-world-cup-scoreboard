import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rayan.scorboard.MatchAlreadyExistsException;
import org.rayan.scorboard.Scoreboard;
import org.rayan.scorboard.Team;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class ScoreboardTest {
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

    @Test
    void testStartNewMatch_whenOneOfTeamAlreadyOnScoreboard(){
        Scoreboard scoreboard = new Scoreboard();
        Team home = new Team( "Mexico" );
        Team away = new Team( "Canada" );
        scoreboard.startNewMatch( home, away );
        Team home1 = new Team( "Mexico" );
        Team away1 = new Team( "Spain" );
        assertThrows( MatchAlreadyExistsException.class, () -> scoreboard.startNewMatch( home1, away1 ) );
        scoreboard.startNewMatch( home, away );
        Team home2 = new Team( "Spain" );
        Team away2 = new Team( "Canada" );
        assertThrows( TeamAlreadyPlayException.class, () -> scoreboard.startNewMatch( home1, away1 ) );
    }
}
