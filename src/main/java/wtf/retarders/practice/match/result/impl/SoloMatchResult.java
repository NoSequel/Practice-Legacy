package wtf.retarders.practice.match.result.impl;

import lombok.Getter;
import org.bukkit.entity.Player;
import wtf.retarders.practice.match.result.IMatchResult;
import wtf.retarders.practice.match.result.MatchResultType;
import wtf.retarders.practice.match.types.SoloMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class SoloMatchResult implements IMatchResult {

    private Player winner, loser;

    private final Map<UUID, Integer> potions, totalHits;
    private final SoloMatch match;

    /**
     * Constructor for creating a new SoloMatchResult
     *
     * @param match the match of the result
     */
    public SoloMatchResult(SoloMatch match) {
        this.match = match;

        this.potions = new HashMap<>();
        this.totalHits = new HashMap<>();
    }

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
                match.handleDeath(this);
            } break;

            case QUIT: {
                match.handleQuit(this);
            } break;
        }
    }
}
