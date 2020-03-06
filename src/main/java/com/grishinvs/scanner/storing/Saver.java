package com.grishinvs.scanner.storing;

import java.util.List;

public interface Saver<T> {

    void save(List<T> list, String path);

}
