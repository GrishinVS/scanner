package com.grishinvs.scanner.directoryscanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Задача по сканированию каталога для потока.
 */
public class ScanTask implements Callable<List<File>> {

    /**
     * Конфигурация
     */
    private final Configuration configuration;

    /**
     * Корневой путь, с которого начнется сканирование
     */
    private final Path rootPath;

    public ScanTask(Configuration configuration, Path rootPath) {
        this.configuration = configuration;
        this.rootPath = rootPath;
    }

    @Override
    public List<File> call() {
        return scanDirectory();
    }

    /**
     * Инициирует выполнение рекурсивного метода сканирования каталога.
     *
     * @return список просканированных файлов.
     */
    private List<File> scanDirectory() {
        List<File> result = new ArrayList<>();
        try {
            startScanDirectory(rootPath, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Обходит указанный каталог, при этом заполняя список, найденными файлами.
     *
     * @param path     путь каталога
     * @param fileList список файлов
     */
    private void startScanDirectory(Path path, List<File> fileList) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path element : directoryStream) {
                File file = element.toFile();
                if (file.isDirectory()) {
                    if (!configuration.getExclusionDirectoryList().contains(file.toPath())) {
                        if (Objects.requireNonNull(file.list()).length != 0) {
                            startScanDirectory(element, fileList);
                        }
                    }
                } else {
                    if (configuration.getExcludesExtensions().stream()
                            .noneMatch(file.getName()::endsWith)) {
                        fileList.add(file);
                    }
                }
            }
        }
    }

}
