package com.grishinvs.scanner.fileprocessing.jsonfileprocessing.jacksonfileprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grishinvs.scanner.fileprocessing.jsonfileprocessing.JsonSerializer;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer<T> implements JsonSerializer<T> {

    @Override
    public void serialize(T t, File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, t);
    }

}
