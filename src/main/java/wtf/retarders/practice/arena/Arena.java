package wtf.retarders.practice.arena;

import org.bukkit.Location;
import wtf.retarders.practice.kit.Kit;

import java.util.List;

public interface Arena {

    /**
     * Get the first spawn location
     *
     * @return the first spawn location
     */
    Location getLocation1();

    /**
     * Get the second spawn location
     *
     * @return the second spawn location
     */
    Location getLocation2();

    /**
     * Get the map's name
     *
     * @return the name of the map
     */
    String getName();

    /**
     * Get the kits which can be used on this map
     *
     * @return a list of the kits
     */
    List<Kit> getKits();

}
