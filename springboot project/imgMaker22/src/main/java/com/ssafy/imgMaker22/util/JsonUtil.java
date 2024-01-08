package com.ssafy.imgMaker22.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonUtil {

    // 객체 생성되지 않도록
    private JsonUtil(){};

    //JsonObject 생성
    public static JsonObject createJson(String... keysAndValues){
        if (keysAndValues.length % 2 != 0) throw new IllegalArgumentException();
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < keysAndValues.length; i+=2) {
            jsonObject.addProperty(keysAndValues[i], keysAndValues[i+1]);
        }
        return jsonObject;
    }

    // JsonObject에 JsonObject add
    public static JsonObject addJsonObject(JsonObject jsonObject, String key, JsonObject value){
        jsonObject.add(key, value);
        return jsonObject;
    }

    // JsonObject에 JsonArray add
    public static JsonObject addJsonObject(JsonObject jsonObject, String key, JsonArray value){
        jsonObject.add(key, value);
        return jsonObject;
    }

    // JsonArray에 JsonObject add
    public static JsonArray addJsonObject(JsonArray jsonObject, JsonObject value){
        jsonObject.add(value);
        return jsonObject;
    }
}
