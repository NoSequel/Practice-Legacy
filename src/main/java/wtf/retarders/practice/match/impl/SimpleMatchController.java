package wtf.retarders.practice.match.impl;

import lombok.Getter;
import org.bukkit.entity.Player;
import wtf.retarders.practice.match.IMatch;
import wtf.retarders.practice.match.MatchController;
import wtf.retarders.practice.match.types.SoloMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SimpleMatchController implements MatchController {

    private final List<IMatch<?>> matches = new ArrayList<>();

    @Override
    public IMatch<?> findMatch(Player player) {
        return matches.stream()
                .filter(match -> match.getPlayers().contains(player))
                .findFirst().orElse(null);
    }
}