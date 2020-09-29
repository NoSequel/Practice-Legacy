package wtf.retarders.practice.data.impl;

import com.google.gson.JsonObject;
import wtf.retarders.practice.data.Data;

public interface SaveableData extends Data {

    String getSavePath();
    JsonObject toJson();

}