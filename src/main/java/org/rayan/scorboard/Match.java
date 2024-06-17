package org.rayan.scorboard;

import java.time.LocalDateTime;

/**
 * @author Rayan Aksu
 * @since 6/17/2024
 */

public interface Match {
    Team getHomeTeam();
    Team getAwayTeam();
    LocalDateTime getMatchStartTime();
}
