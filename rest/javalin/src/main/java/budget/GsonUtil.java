// src/main/java/budget/GsonUtil.java
package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import io.javalin.json.JsonMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jetbrains.annotations.NotNull;

public class GsonUtil {
	
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (date, type, ctx) -> ctx.serialize(date.format(DateTimeFormatter.ISO_LOCAL_DATE)))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, ctx) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
            .setPrettyPrinting()
            .create();

    public static Gson getGson() { return gson; }
    
    public static final JsonMapper jsonMapper = new JsonMapper() {
        @NotNull
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) { return gson.toJson(obj, type); }
        @NotNull
        @Override
        public InputStream toJsonStream(@NotNull Object obj, @NotNull Type type) { return new ByteArrayInputStream(toJsonString(obj, type).getBytes(StandardCharsets.UTF_8)); }
        @NotNull
        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) { return gson.fromJson(json, targetType); }
        @NotNull
        @Override
        public <T> T fromJsonStream(@NotNull InputStream stream, @NotNull Type targetType) {
            try {
                String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                return fromJsonString(json, targetType);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON stream", e);
            }
        }
    };
    
}
