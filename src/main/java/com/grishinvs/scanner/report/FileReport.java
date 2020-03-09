package com.grishinvs.scanner.report;

import java.text.SimpleDateFormat;

public class FileReport {

    private static final String DATE_PATTERN = "yyyy.MM.dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    private String path;
    private long lastModified;
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
                "date = " + dateFormat.format(lastModified) + System.lineSeparator() +
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
