package com.grishinvs.scanner.report;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Реализация обработчика отчетов
 */
public class ReportHandlerImpl implements ReportHandler {

    @Override
    public String createReport(List<File> fileList) {
        StringBuilder stringBuilder = new StringBuilder();
        fileList.stream()
                .map(item -> new FileReport(item.getAbsolutePath(), item.lastModified(), item.length()))
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    @Override
    public void saveReport(String report, Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.writeString(path, report, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
