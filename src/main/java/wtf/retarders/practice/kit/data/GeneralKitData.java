package wtf.retarders.practice.kit.data;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import wtf.retarders.practice.data.impl.SaveableData;
import wtf.retarders.practice.util.JsonAppender;

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

    /**
     * Constructor for loading a GeneralKitData object from a JsonObject
     *
     * @param object the JsonObject it's loaded from
     */
    public GeneralKitData(JsonObject object) {
        this.kitName = object.get("kitName").getAsString();
        this.displayName = object.get("displayName").getAsString();
        this.icon = Material.valueOf(object.get("icon").getAsString());
    }

    @Override
    public JsonObject toJson() {
        return new JsonAppender()
                .append("kitName", this.kitName)
                .append("displayName", this.displayName)
                .append("icon", this.icon.name()).get();
    }
}
