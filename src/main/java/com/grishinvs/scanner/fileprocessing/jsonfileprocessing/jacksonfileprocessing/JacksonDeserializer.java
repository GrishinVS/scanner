package com.grishinvs.scanner.fileprocessing.jsonfileprocessing.jacksonfileprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grishinvs.scanner.fileprocessing.jsonfileprocessing.JsonDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonDeserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(File file, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, tClass);
    }

    @Override
    public List<T> deserializeList(File file, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, objectMapper.getTypeFactory()
                .constructCollectionLikeType(List.class, tClass));
    }

}
