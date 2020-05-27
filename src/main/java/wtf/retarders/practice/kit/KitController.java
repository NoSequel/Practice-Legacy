package wtf.retarders.practice.kit;

import wtf.retarders.practice.controller.Controller;

import java.util.List;

public interface KitController extends Controller {

    /**
     * get all registered kits.
     *
     * @return the list of kits
     */
    List<Kit> getKits();

    /**
     * get a kit by name
     *
     * @param kitName the name of the kit
     * @return the kit
     */
    Kit findKit(String kitName);

}
