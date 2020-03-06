package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScanTask implements Runnable {

    private final Configuration configuration;
    private boolean isRunning;

    public ScanTask(Configuration configuration) {
        this.configuration = configuration;
        this.isRunning = true;
    }

    public void run() {
        scanDirectory();
    }

    private void scanDirectory() {
        List<ScanFile> result = new ArrayList<>();
        try {
            startScanDirectory(Paths.get("чудо путь"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startScanDirectory(Path path, List<ScanFile> fileList) throws IOException {
        while (isRunning) {
            try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path element : directoryStream) {
                    File file = element.toFile();
                    if (file.isDirectory()) {
                        if (Objects.requireNonNull(file.list()).length != 0) {
                            startScanDirectory(element, fileList);
                        }
                    } else {
                        if (configuration.getFileExtension().stream().anyMatch(item -> file.getAbsolutePath().endsWith(item))) {
                            fileList.add(new ScanFile(file.getAbsolutePath(), file.lastModified(),
                                    file.getParentFile().getName()));
                        }
                    }
                }
            }
        }
    }

}
