package com.grishinvs.scanner.configuration;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Конфигурационный класс. Содержит параметры программы.
 */
public class Configuration {

    /**
     * Каталоги, подлежащие сканированию
     */
    private List<Path> directoryList;

    /**
     * Каталоги, не подлежащие сканированию
     */
    private List<Path> exclusionDirectoryList;

    /**
     * Расширения файлов, подлежаще сканированию
     */
    private List<String> excludesExtensions;

    /**
     * Количество потоков, используемых при сканировании каталогов
     */
    private int threadNumber;

    public Configuration() {
        directoryList = new ArrayList<>();
        excludesExtensions = new ArrayList<>();
        exclusionDirectoryList = new ArrayList<>();
        threadNumber = 1;
    }

    public static class Builder {

        private List<Path> directoryList;
        private List<String> excludesExtensions;
        private List<Path> exclusionList;
        private int threadNumber;

        public Builder setDirectoryList(List<Path> directoryList) {
            this.directoryList = directoryList;
            return this;
        }

        public Builder setExclusionList(List<Path> exclusionList) {
            this.exclusionList = exclusionList;
            return this;
        }

        public Builder setExcludesExtensions(List<String> excludesExtensions) {
            this.excludesExtensions = excludesExtensions;
            return this;
        }

        public Builder setThreadNumber(int threadNumber) {
            this.threadNumber = threadNumber;
            return this;
        }

        public Configuration build() {
            Configuration configuration = new Configuration();
            configuration.directoryList = this.directoryList;
            configuration.exclusionDirectoryList = this.exclusionList;
            configuration.excludesExtensions = this.excludesExtensions;
            configuration.threadNumber = this.threadNumber;
            return configuration;
        }

    }

    public List<Path> getDirectoryList() {
        return directoryList;
    }

    public List<Path> getExclusionDirectoryList() {
        return exclusionDirectoryList;
    }

    public List<String> getExcludesExtensions() {
        return excludesExtensions;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public void setDirectoryList(List<Path> directoryList) {
        this.directoryList = directoryList;
    }

    public void setExclusionDirectoryList(List<Path> exclusionDirectoryList) {
        this.exclusionDirectoryList = exclusionDirectoryList;
    }

    public void setExcludesExtensions(List<String> excludesExtensions) {
        this.excludesExtensions = excludesExtensions;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

}
