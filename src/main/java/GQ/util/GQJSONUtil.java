package GQ.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 帝 on 2017/2/12.
 */
public final class GQJSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJSON(T o){
        //return OBJECT_MAPPER.convertValue(o, String.class);
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJSON(String o, Class<T> t){
        try {
            return OBJECT_MAPPER.readValue(o, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
