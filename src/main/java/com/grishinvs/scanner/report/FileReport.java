package com.grishinvs.scanner.report;

import java.text.SimpleDateFormat;

/**
 * Представляет собой отчет о просканированном файле.
 */
public class FileReport {

    /**
     * Шаблон даты
     */
    private static final String DATE_PATTERN = "yyyy.MM.dd";

    /**
     * Объект для форматирования и парсинга дат
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    /**
     * Путь к файлу
     */
    private String path;

    /**
     * Последнее время модификации
     */
    private long lastModified;

    /**
     * Размер
     */
    private long size;

    public FileReport() {
    }

    public FileReport(String path, long lastModified, long size) {
        this.path = path;
        this.lastModified = lastModified;
        this.size = size;
    }

    @Override
    public String toString() {
        return "[" + System.lineSeparator() +
                "file = " + path + System.lineSeparator() +
                "date = " + DATE_FORMAT.format(lastModified) + System.lineSeparator() +
                "size = " + size +
                ']';
    }

    public String getPath() {
        return path;
    }

    public long getLastModified() {
        return lastModified;
    }

    public long getSize() {
        return size;
    }

}
