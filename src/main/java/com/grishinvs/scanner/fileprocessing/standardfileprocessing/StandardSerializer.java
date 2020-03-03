package com.grishinvs.scanner.fileprocessing.standardfileprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StandardSerializer<T> implements Serializer<T> {

    @Override
    public void serialize(T target, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(target);
            oos.flush();
        }
    }

}
