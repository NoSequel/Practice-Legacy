package wtf.retarders.practice.match;

import org.bukkit.entity.Player;
import wtf.retarders.practice.controller.Controller;

import java.util.List;

public interface MatchController extends Controller {

    /**
     * Find a match by player
     *
     * @param player the player
     * @return the match
     */
    IMatch<?> findMatch(Player player);

    /**
     * Get all matches
     *
     * @return the matches list
     */
    List<IMatch<?>> getMatches();

}
