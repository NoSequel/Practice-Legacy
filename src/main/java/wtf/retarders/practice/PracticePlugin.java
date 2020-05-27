package wtf.retarders.practice;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.retarders.practice.controller.ControllerRegistrar;
import wtf.retarders.practice.kit.impl.SimpleKitController;
import wtf.retarders.practice.match.impl.SimpleMatchController;

@Getter
public class PracticePlugin extends JavaPlugin {

    private ControllerRegistrar handler;

    public void onEnable() {
        this.handler = new ControllerRegistrar(
                new SimpleMatchController(),
                new SimpleKitController()
        );
    }

    public static PracticePlugin getPlugin() {
        return JavaPlugin.getPlugin(PracticePlugin.class);
    }

}
