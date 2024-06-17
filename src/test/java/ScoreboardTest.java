/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public class ScoreboardTest {
    @Test
    void testCreateNewMatch_whenNewMatchStart() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startNewMatch( "Mexico", "Canada" );
        assertEquals( 1, scoreboard.getMatches().size() );
    }

}
