package wtf.retarders.practice.kit.data;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import wtf.retarders.practice.data.impl.SaveableData;

@Getter
@Setter
public class GeneralKitData implements SaveableData {

    private final String savePath = "general";

    private String kitName, displayName;

    private ItemStack[] contents, armor;
    private Material icon;

    /**
     * Constructor to make a completely new GeneralKitData object
     *
     * @param kitName the name
     */
    public GeneralKitData(String kitName) {
        this.kitName = kitName;
        this.displayName = "&f" + kitName;
        this.icon = Material.DIAMOND_SWORD;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
