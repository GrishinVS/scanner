package com.grishinvs.scanner.report;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Создатель отчетов
 */
public interface ReportHandler {

    String createReport(List<File> list);

    void saveReport(String report, Path path);

}
