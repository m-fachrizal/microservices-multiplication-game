package fachrizal.microservices.gamification.game;

import fachrizal.microservices.gamification.challenge.ChallengeSolvedDTO;
import fachrizal.microservices.gamification.game.domain.BadgeType;
import lombok.Value;

import java.util.List;

public interface GameService {

/**
 * Process a new attempt from a given user.
 * @param challenge the challenge data with user details, factors, etc.
 * @return a {@link GameResult} object containing the new score and badge
 * cards obtained
 */

    GameResult newAttemptForUser(ChallengeSolvedDTO challenge);

    /**
     * The output after processing the attempt is a GameResult object, defined within the
     * interface. It groups the score obtained from that attempt together with any new badge
     * that the user may get. We could also consider not returning anything since it’ll be the
     * leaderboard logic showing the results. However, it’s better to have a response from our
     * method so we can test it.
     * The ChallengeSolvedDTO class defines the contract between the Multiplication
     * and Gamification microservices, and we’ll create it in both projects to keep them
     * independent.
     */
    @Value
    class GameResult {
        int score;
        List<BadgeType> badges;
    }
}
