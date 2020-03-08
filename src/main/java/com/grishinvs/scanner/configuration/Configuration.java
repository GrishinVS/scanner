package com.grishinvs.scanner.configuration;

import java.nio.file.Path;
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
    private List<Path> exclusionList;

    /**
     * Расширения файлов, подлежаще сканированию
     */
    private List<String> excludesExtensions;

    /**
     * Количество потоков, используемых при сканировании каталогов
     */
    private int threadNumber;

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
            configuration.exclusionList = this.exclusionList;
            configuration.excludesExtensions = this.excludesExtensions;
            configuration.threadNumber = this.threadNumber;
            return configuration;
        }


    }
    public List<Path> getDirectoryList() {
        return directoryList;
    }

    public List<Path> getExclusionList() {
        return exclusionList;
    }

    public List<String> getExcludesExtensions() {
        return excludesExtensions;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

}
