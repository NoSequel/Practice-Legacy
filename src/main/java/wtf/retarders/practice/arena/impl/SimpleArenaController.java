package wtf.retarders.practice.arena.impl;

import lombok.Getter;
import wtf.retarders.practice.arena.Arena;
import wtf.retarders.practice.arena.ArenaController;
import wtf.retarders.practice.arena.data.GeneralDataArena;
import wtf.retarders.practice.kit.Kit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
public class SimpleArenaController implements ArenaController {

    private final List<Arena> arenas = new ArrayList<>();

    @Override
    public Arena getRandomArena(Kit kit) {
        return arenas.stream()
                .filter(arena -> arena.hasData(GeneralDataArena.class))
                .filter(arena -> arena.findData(GeneralDataArena.class).getKits().contains(kit))
                .collect(Collectors.toList()).get(ThreadLocalRandom.current().nextInt(arenas.size()));
    }

    @Override
    public Arena findArena(String name) {
        return arenas.stream()
                .filter(arena -> arena.hasData(GeneralDataArena.class))
                .filter(arena -> arena.findData(GeneralDataArena.class).getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}