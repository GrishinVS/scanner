package com.grishinvs.scanner.fileprocessing.standardfileprocessing;

import java.io.File;
import java.io.IOException;

public interface Serializer<T> {

    void serialize(T target, File file) throws IOException;

}
