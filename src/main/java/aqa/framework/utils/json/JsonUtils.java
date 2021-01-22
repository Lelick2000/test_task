package aqa.framework.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public final class JsonUtils {

    private static final Gson gson = new Gson();

    private JsonUtils() {
    }

    public static <T> T getObjectFromJsonString(JsonElement jsonElement, Class<T> inputObject) {
        return gson.fromJson(jsonElement, inputObject);
    }

    public static <T> T getObjectFromJsonString(JsonElement jsonElement, Type inputObject) {
        return gson.fromJson(jsonElement, inputObject);
    }

    public static <T> T getObjectFromJsonString(String jsonBody, Type inputObject) {
        return gson.fromJson(jsonBody, inputObject);
    }

    /**
     * Маппинг JSON объекта на экземпляр класса.
     *
     * @param filePath    путь к JSON файлу для чтения.
     * @param key         ключ для поиска объекта в JSON.
     * @param targetClass тип объекта, на которые будет мапится объект из JSON.
     * @param <T>         обобщенный тип объекта, на которые будет мапится объект из JSON.
     * @return объект, извлеченный из JSON.
     */
    public static <T> T mapToObject(String filePath, String key, Class<T> targetClass) {
        JsonObject jsonObject = readFromFileObject(filePath).getAsJsonObject(key);
        return gson.fromJson(jsonObject, targetClass);
    }

    public static JsonObject readFromFileObject(String filePath) {
        return readFromFile(filePath).getAsJsonObject();
    }

    public static JsonArray readFromFileArray(String filePath) {
        return readFromFile(filePath).getAsJsonArray();
    }

    private static JsonElement readFromFile(String filePath) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read parameter from the file " + filePath);
        }
        return jsonElement;
    }

    /**
     * Маппинг JSON объекта на экземпляр класса.
     *
     * @param json JSON.
     * @param type тип объекта, на которые будет мапится объект из JSON.
     * @param <T>  обобщенный тип объекта, на которые будет мапится объект из JSON.
     * @return объект, извлеченный из JSON.
     */
    public static <T> T mapToObject(JsonObject json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * Получение массива строк из объекта JSON по ключу.
     *
     * @param jsonObject экземпляр JSONObject.
     * @param key        ключ для полиска массива строк в объекте JSON.
     * @return массив строк, извлеченный из JSON объекта.
     */
    public static String[] getStringArray(JsonObject jsonObject, String key) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(key);
        return gson.fromJson(jsonArray, new TypeToken<String[]>() {
        }.getType());
    }
}
