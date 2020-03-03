package com.grishinvs.scanner.fileprocessing.standardfileprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StandardDeserializer<T> implements Deserializer<T> {

    @Override
    public T deserialize(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream oin = new ObjectInputStream(fis)) {
            return (T) oin.readObject();
        }
    }

}
