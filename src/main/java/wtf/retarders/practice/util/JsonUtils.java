package wtf.retarders.practice.util;

import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    @Getter
    private final JsonParser parser = new JsonParser();

}
