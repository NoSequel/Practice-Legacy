package wtf.retarders.practice.kit;

import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import wtf.retarders.practice.PracticePlugin;

import java.util.Arrays;
import java.util.Collections;

@Data
public class Kit {

    private String kitName, displayName;

    private ItemStack[] contents, armor;
    private Material icon;

    /**
     * Constructor for creating a new kit
     *
     * @param kitName the name of the kit
     */
    public Kit(String kitName) {
        this.kitName = kitName;
        this.displayName = "&f" + kitName;
        this.icon = Material.DIAMOND_SWORD;

        PracticePlugin.getPlugin().getHandler().findHandler(KitController.class).getKits().add(this);
    }

    /**
     * Apply a kit to a player
     *
     * @param player the player to apply it to
     */
    public void apply(Player player) {
        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armor);
    }
}