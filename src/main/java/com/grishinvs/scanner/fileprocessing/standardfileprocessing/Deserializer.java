package com.grishinvs.scanner.fileprocessing.standardfileprocessing;

import java.io.File;
import java.io.IOException;

public interface Deserializer<T> {

    T deserialize(File file) throws IOException, ClassNotFoundException;

}
