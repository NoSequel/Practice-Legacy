package wtf.retarders.practice.arena.data;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import wtf.retarders.practice.data.impl.SaveableData;
import wtf.retarders.practice.kit.Kit;
import wtf.retarders.practice.util.JsonAppender;

import java.util.List;

@Getter
@Setter
public class GeneralDataArena implements SaveableData {

    private final String savePath = "general";

    private Location location1;
    private Location location2;

    private String name;
    private List<Kit> kits;

    /**
     * Constructor for loading a GeneralDataArena from a JsonObject
     *
     * @param object the json object
     */
    public GeneralDataArena(JsonObject object) {
        // this.location1 = todo: deserialize
        // this.location2 = todo: desrialize

        this.name = object.get("name").getAsString();
        // this.kits = todo: deserialize
    }

    @Override
    public JsonObject toJson() {
        return new JsonAppender()
                .append("location1", location1.toString())
                .append("location2", location2.toString())
                .append("name", this.name)
                .append("kits", this.kits.toString()).get();
    }
}