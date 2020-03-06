package com.grishinvs.scanner.fileprocessing.jsonfileprocessing;

import com.grishinvs.scanner.fileprocessing.jsonfileprocessing.jacksonfileprocessing.JacksonDeserializer;
import com.grishinvs.scanner.fileprocessing.jsonfileprocessing.jacksonfileprocessing.JacksonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileHandler<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private JsonSerializer<T> serializer;
    private JsonDeserializer<T> deserializer;

    public JsonFileHandler() {
        this.serializer = new JacksonSerializer<>();
        this.deserializer = new JacksonDeserializer<>();
    }

    public JsonFileHandler(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public void serialize(T target, File file) throws IOException {
        serializer.serialize(target, file);
    }

    @Override
    public T deserialize(File file, Class<T> tClass) throws IOException {
        return deserializer.deserialize(file, tClass);
    }

    @Override
    public List<T> deserializeList(File file, Class<T> tClass) throws IOException {
        return deserializer.deserializeList(file, tClass);
    }

}
