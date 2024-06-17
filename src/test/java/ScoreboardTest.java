import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

}
