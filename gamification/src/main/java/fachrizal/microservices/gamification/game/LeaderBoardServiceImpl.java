package fachrizal.microservices.gamification.game;

import fachrizal.microservices.gamification.game.domain.LeaderBoardRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        // Get score only
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();
        // Combine with badges
        return scoreOnly.stream().map(row -> {
            List<String> badges =
                    badgeRepository.findByUserIdOrderByBadgeTimestampDesc(
                            row.getUserId()).stream()
                            .map(b -> b.getBadgeType().getDescription())
                            .collect(Collectors.toList());
            /**
             * Note that we used the method withBadges to copy an immutable object with a new
             * value. The first time we generate the leaderboard, all rows have an empty list of badges.
             * When we collect the badges, we can replace (using stream’s map) each object with a copy
             * with the corresponding badge list.
             */
            return row.withBadges(badges);
        }).collect(Collectors.toList());
    }
}
