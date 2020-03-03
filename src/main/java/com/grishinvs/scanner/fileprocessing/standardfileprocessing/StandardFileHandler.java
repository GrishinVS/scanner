package com.grishinvs.scanner.fileprocessing.standardfileprocessing;

import java.io.File;
import java.io.IOException;

public class StandardFileHandler<T> implements Serializer<T>, Deserializer<T> {

    private Serializer<T> serializer;
    private Deserializer<T> deserializer;

    public StandardFileHandler() {
        this.serializer = new StandardSerializer<>();
        this.deserializer = new StandardDeserializer<>();
    }

    public StandardFileHandler(Serializer<T> serializer, Deserializer<T> deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public void serialize(T target, File file) throws IOException {
        serializer.serialize(target, file);
    }

    @Override
    public T deserialize(File file) throws IOException, ClassNotFoundException {
        return deserializer.deserialize(file);
    }

}
