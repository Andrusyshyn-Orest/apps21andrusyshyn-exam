package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> map;

    public JsonObject(JsonPair... jsonPairs) {
        map = new HashMap<>();
        for (JsonPair jsonPair: jsonPairs) {
            String key = jsonPair.key;
            Json value = jsonPair.value;
            map.put(key, value);
        }
    }

    public Map getMap(){
        return this.map;
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjBody() + "}";
    }

    private String getJsonObjBody() {
        StringBuilder jsonStr = new StringBuilder();
        int length = map.size();
        int count = 0;
        for (String key: map.keySet()) {
            count++;
            JsonPair jsonPair = new JsonPair(key, map.get(key));
            jsonStr.append(jsonPair.toJson());
            if (count != length) {
                jsonStr.append(", ");
            }
        }
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        map.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        if (this.contains(name)){
            return map.get(name);
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObj = new JsonObject();
        for (String name : names){
            if (this.contains(name)){
                jsonObj.add(new JsonPair(name, map.get(name)));
            }
        }
        return jsonObj;
    }

    public boolean contains(String name){
        if (map.keySet().contains(name)){
            return true;
        }
        return false;
    }
}
