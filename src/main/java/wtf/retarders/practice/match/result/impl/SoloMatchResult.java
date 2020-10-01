package wtf.retarders.practice.match.result.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import wtf.retarders.practice.match.result.IMatchResult;
import wtf.retarders.practice.match.result.MatchResultType;
import wtf.retarders.practice.match.types.SoloMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class SoloMatchResult implements IMatchResult {

    private Player winner, loser;

    private final Map<UUID, Integer> potions = new HashMap<>(), totalHits = new HashMap<>();
    private final SoloMatch match;

    /**
     * End the match
     *
     * @param winner the winner of the match
     * @param loser  the loser of the match
     * @param type   the type of match result
     */
    public void endMatch(Player winner, Player loser, MatchResultType type) {
        this.winner = winner;
        this.loser = loser;

        switch (type) {
            case DIED: {
                match.handleDeath();
            } break;

            case QUIT: {
                match.handleQuit();
            } break;
        }
    }
}
