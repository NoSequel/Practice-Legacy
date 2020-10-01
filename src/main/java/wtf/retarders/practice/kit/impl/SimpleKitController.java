package wtf.retarders.practice.kit.impl;

import lombok.Getter;
import lombok.Setter;
import wtf.retarders.practice.kit.Kit;
import wtf.retarders.practice.kit.KitController;
import wtf.retarders.practice.kit.data.GeneralKitData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SimpleKitController implements KitController {

    private List<Kit> kits;

    public SimpleKitController() {
        this.kits = new ArrayList<>();
    }

    @Override
    public Kit findKit(String kitName) {
        return this.kits.stream()
                .filter(kit -> kit.hasData(GeneralKitData.class))
                .filter(kit -> kit.findData(GeneralKitData.class).getKitName().equalsIgnoreCase(kitName))
                .findFirst().orElse(null);
    }
}