package com.grishinvs.scanner.directoryscanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.io.File;
import java.util.List;

/**
 * Сканер каталогов
 */
public interface DirectoryScanner {

    /**
     * Сканирует каталоги, указанные в конфигураци.
     *
     * @param configuration конфигурация
     * @return список файлов
     */
    List<File> scan(Configuration configuration);

}
