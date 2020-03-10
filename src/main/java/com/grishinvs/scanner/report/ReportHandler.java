package com.grishinvs.scanner.report;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Обработчик отчетов
 */
public interface ReportHandler {

    /**
     * Создает отчет из списка файлов.
     *
     * @param fileList Список файлов, из которых будет составлен отчет
     * @return отчет
     */
    String createReport(List<File> fileList);

    /**
     * Сохраняет отчет в файл.
     *
     * @param report отчет
     * @param path   путь к файлу
     */
    void saveReport(String report, Path path);

}
