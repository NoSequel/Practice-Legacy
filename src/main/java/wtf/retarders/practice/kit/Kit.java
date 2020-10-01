package wtf.retarders.practice.kit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import wtf.retarders.practice.PracticePlugin;
import wtf.retarders.practice.data.Data;
import wtf.retarders.practice.data.Loadable;
import wtf.retarders.practice.kit.data.GeneralKitData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Kit implements Loadable<Data> {

    private List<Data> data = new ArrayList<>();
    private UUID uniqueId;

    /**
     * Constructor for creating a new kit
     *
     * @param kitName the name of the kit
     */
    public Kit(String kitName, UUID uuid) {
        this.uniqueId = uuid;

        this.addData(new GeneralKitData(kitName));
        PracticePlugin.getPlugin().getHandler().findHandler(KitController.class).getKits().add(this);
    }

    /**
     * Apply a kit to a player
     *
     * @param player the player to apply it to
     */
    public void apply(Player player) {
        final GeneralKitData data = this.findData(GeneralKitData.class);

        if (data != null) {
            player.getInventory().setContents(data.getContents());
            player.getInventory().setArmorContents(data.getArmor());
        }
    }
}