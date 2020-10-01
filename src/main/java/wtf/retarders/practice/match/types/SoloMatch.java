package wtf.retarders.practice.match.types;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import wtf.retarders.practice.PracticePlugin;
import wtf.retarders.practice.arena.Arena;
import wtf.retarders.practice.arena.data.GeneralDataArena;
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
        final GeneralDataArena data = arena.findData(GeneralDataArena.class);

        if (data != null) {
            player1.teleport(data.getLocation1());
            player2.teleport(data.getLocation2());
        }

        this.runningTask = new SoloMatchTask(this);
    }

    @Override
    public void handleDeath() {
        this.endMatch();
        this.broadcast("&c" + result.getLoser().getName() + " &7has been killed by &a" + result.getWinner().getName());
    }

    @Override
    public void handleQuit() {
        this.endMatch();
        this.broadcast("&c" + result + " &7has disconnected.");
    }


    /**
     * Method called upon the ending of a match
     **/
    private void endMatch() {
        this.matchController.getMatches().remove(this);
        this.runningTask.cancel();

        Bukkit.getPluginManager().callEvent(new MatchEndEvent(this));

        this.broadcast(new String[]{
                "",
                "&eWinner: &a" + result.getWinner().getName() + " &6[" + result.getPotions(result.getWinner()) + "]",
                "&cLoser: &c" + result.getLoser().getName() + " &6[" + result.getPotions(result.getLoser()) + "]",
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