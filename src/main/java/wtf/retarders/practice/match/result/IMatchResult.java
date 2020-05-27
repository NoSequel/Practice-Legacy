package wtf.retarders.practice.match.result;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface IMatchResult {

    /**
     * Get the potion map
     *
     * @return the map
     */
    Map<UUID, Integer> getPotions();

    /**
     * Get the total hit map
     *
     * @return the hit map
     */
    Map<UUID, Integer> getTotalHits();

    /**
     * Add a hit to the player's key
     *
     * @param player the player
     */
    default void addHit(Player player) {
        this.getTotalHits().put(player.getUniqueId(), this.getTotalHits().get(player.getUniqueId())+1);
    }

    /**
     * Get the amount of potions a player had left
     *
     * @param player the player
     * @return the amount of potions
     */
    default int getPotions(Player player) {
        return this.getPotions().get(player.getUniqueId());
    }

}
