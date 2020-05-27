package wtf.retarders.practice.match;

import org.bukkit.entity.Player;
import wtf.retarders.practice.arena.Arena;
import wtf.retarders.practice.kit.Kit;
import wtf.retarders.practice.match.result.IMatchResult;
import wtf.retarders.practice.task.AbstractTask;

import java.util.List;
import java.util.function.Consumer;

public interface IMatch<T> {

    /**
     * Start the match
     */
    void startMatch();

    /**
     * Handle death of player
     *
     * @param matchResult the result of the match
     */
    void handleDeath(IMatchResult matchResult);

    /**
     * Handle quit of player
     *
     * @param matchResult the result of the match
     */
    void handleQuit(IMatchResult matchResult);

    /**
     * Broadcast a message in a match
     *
     * @param message the message to broadcast
     */
    void broadcast(String message);

    /**
     * Broadcast a couple of messsages in a match
     *
     * @param messages the messages to be broadcasted
     */
    void broadcast(String[] messages);

    /**
     * Call an action for all players in the match
     *
     * @param action the action
     */
    void callGlobalAction(Consumer<Player> action);

    /**
     * Get the current running task
     *
     * @return the running task
     */
    AbstractTask getRunningTask();

    /**
     * Get the kit of the match
     *
     * @return the kit
     */
    Kit getKit();

    /**
     * Get the arena of the match
     *
     * @return the arena
     */
    Arena getArena();

    /**
     * Get the players which are in the match
     *
     * @return the list of players
     */
    List<Player> getPlayers();

    /**
     * Get opponent of someone
     *
     * @param toCheck the object to check
     * @return the opponent
     */
    T getOpponent(T toCheck);

}