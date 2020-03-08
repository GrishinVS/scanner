package com.grishinvs.scanner;

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

public class ScanTask implements Callable<List<ScanFile>> {

    private final Configuration configuration;
    private final Path startingPath;

    public ScanTask(Configuration configuration, Path startingPath) {
        this.configuration = configuration;
        this.startingPath = startingPath;
    }

    public List<ScanFile> call() {
        return scanDirectory();
    }

    private List<ScanFile> scanDirectory() {
        List<ScanFile> result = new ArrayList<>();
        try {
            startScanDirectory(startingPath, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void startScanDirectory(Path path, List<ScanFile> fileList) throws IOException {
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
                        ScanFile scanFile = new ScanFile(file.getAbsolutePath(), file.lastModified(),
                                file.getParentFile().getName());
                        fileList.add(scanFile);
                    }
                }
            }
        }
    }

}
