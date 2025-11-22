package com.weavers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JSONArray readJsonArray(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            return (JSONArray) obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath);
        }
    }

    public static JSONObject readJsonObject(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            return (JSONObject) obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath);
        }
    }

    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert object to JSON string");
        }
    }

    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to object");
        }
    }
    
    public static JSONObject getJsonObjectFromArray(JSONArray jsonArray, int index) {
        if (jsonArray != null && index < jsonArray.size()) {
            return (JSONObject) jsonArray.get(index);
        }
        throw new RuntimeException("Invalid index or null array");
    }
}