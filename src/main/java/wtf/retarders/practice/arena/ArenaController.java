package wtf.retarders.practice.arena;

import wtf.retarders.practice.controller.Controller;
import wtf.retarders.practice.kit.Kit;

import java.util.List;

public interface ArenaController extends Controller {

    /**
     * Get all loaded arenas
     *
     * @return the loaded arenas
     */
    List<Arena> getArenas();

    /**
     * Get a random arena
     *
     * @return the random arena
     */
    Arena getRandomArena(Kit kit);

    /**
     * Find an arena by a name
     *
     * @param name the name of the arena
     * @return the found arena
     */
    Arena findArena(String name);

}