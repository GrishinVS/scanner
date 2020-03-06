package com.grishinvs.scanner.fileprocessing.jsonfileprocessing;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JsonDeserializer<T> {

    T deserialize(File file, Class<T> tClass) throws IOException;

    List<T> deserializeList(File file, Class<T> tClass) throws IOException;

}
