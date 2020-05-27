package wtf.retarders.practice.match.types;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import wtf.retarders.practice.PracticePlugin;
import wtf.retarders.practice.arena.Arena;
import wtf.retarders.practice.event.match.MatchEndEvent;
import wtf.retarders.practice.event.match.MatchStartEvent;
import wtf.retarders.practice.kit.Kit;
import wtf.retarders.practice.match.IMatch;
import wtf.retarders.practice.match.MatchController;
import wtf.retarders.practice.match.result.IMatchResult;
import wtf.retarders.practice.match.result.impl.SoloMatchResult;
import wtf.retarders.practice.match.task.SoloMatchTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
public class SoloMatch implements IMatch<Player> {

    private final Player player1;
    private final Player player2;

    private final MatchController matchController = PracticePlugin.getPlugin().getHandler().findHandler(MatchController.class);
    private final List<Player> players = new ArrayList<>();

    private final SoloMatchResult result;

    private final Kit kit;
    private final Arena arena;

    private SoloMatchTask runningTask;


    /**
     * Constructor for creating a new SoloMatch
     *
     * @param player1 the first player
     * @param player2 the second player
     * @param kit     the kit the players will receive
     */
    public SoloMatch(Player player1, Player player2, Kit kit, Arena arena) {
        this.player1 = player1;
        this.player2 = player2;
        this.kit = kit;
        this.arena = arena;
        this.result = new SoloMatchResult(this);

        this.players.addAll(Arrays.asList(player1, player2));
        this.matchController.getMatches().add(this);
    }

    @Override
    public void startMatch() {
        Bukkit.getPluginManager().callEvent(new MatchStartEvent(this));

        this.callGlobalAction(kit::apply);

        player1.teleport(arena.getLocation1());
        player2.teleport(arena.getLocation2());

        this.runningTask = new SoloMatchTask(this);
    }

    @Override
    public void handleDeath(IMatchResult matchResult) {
        SoloMatchResult soloMatchResult = (SoloMatchResult) matchResult;

        this.endMatch(soloMatchResult);
        this.broadcast("&c" + soloMatchResult.getLoser().getName() + " &7has been killed by &a" + soloMatchResult.getWinner().getName());
    }

    @Override
    public void handleQuit(IMatchResult matchResult) {
        SoloMatchResult soloMatchResult = (SoloMatchResult) matchResult;

        this.endMatch(soloMatchResult);
        this.broadcast("&c" + matchResult + " &7has disconnected.");
    }


    /**
     * Method called upon the ending of a match
     *
     * @param matchResult the result of the match
     */
    private void endMatch(SoloMatchResult matchResult) {
        this.matchController.getMatches().remove(this);
        this.runningTask.cancel();

        Bukkit.getPluginManager().callEvent(new MatchEndEvent(this));

        this.broadcast(new String[]{
                "",
                "&eWinner: &a" + matchResult.getWinner().getName() + " &6[" + matchResult.getPotions(matchResult.getWinner()) + "]",
                "&cLoser: &c" + matchResult.getLoser().getName() + " &6[" + matchResult.getPotions(matchResult.getLoser()) + "]",
                "",
        });
    }

    @Override
    public void broadcast(String message) {
        this.callGlobalAction(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    @Override
    public void broadcast(String[] messages) {
        Arrays.stream(messages).forEach(this::broadcast);
    }

    @Override
    public void callGlobalAction(Consumer<Player> action) {
        this.getPlayers().stream()
                .filter(player -> player != null && player.isOnline())
                .forEach(action);
    }

    @Override
    public Player getOpponent(Player player) {
        return player1.equals(player) ? player2 : player2.equals(player) ? player1 : null;
    }
}