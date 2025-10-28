package com.weavers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Read JSON data from file and return as JSONArray
     */
    public static JSONArray readJsonArray(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            return (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + filePath);
        }
    }

    /**
     * Read JSON data from file and return as JSONObject
     */
    public static JSONObject readJsonObject(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + filePath);
        }
    }

    /**
     * Convert Object to JSON String
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert object to JSON string");
        }
    }

    /**
     * Convert JSON String to Object
     */
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert JSON string to object");
        }
    }

    /**
     * Get JSONObject from JSONArray by index
     */
    public static JSONObject getJsonObjectFromArray(JSONArray jsonArray, int index) {
        if (jsonArray != null && index < jsonArray.size()) {
            return (JSONObject) jsonArray.get(index);
        }
        throw new RuntimeException("Invalid index or null array");
    }
}