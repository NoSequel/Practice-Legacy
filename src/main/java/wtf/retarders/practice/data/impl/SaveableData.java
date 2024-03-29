package wtf.retarders.practice.data.impl;

import com.google.gson.JsonObject;
import wtf.retarders.practice.data.Data;

public interface SaveableData extends Data {

    /**
     * Get the save path where the SaveableData object gets stored
     *
     * @return the path
     */
    String getSavePath();

    /**
     * Get the object which gets stored in the database
     *
     * @return the object
     */
    JsonObject toJson();

}