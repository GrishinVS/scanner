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
    private List<Path> fileExtension;

    public static class Builder {

        private List<Path> directoryList;
        private List<Path> fileExtension;
        private List<Path> exclusionList;

        public Builder setDirectoryList(List<Path> directoryList) {
            this.directoryList = directoryList;
            return this;
        }

        public Builder setExclusionList(List<Path> exclusionList) {
            this.exclusionList = exclusionList;
            return this;
        }

        public Builder setFileExtension(List<Path> fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        public Configuration build() {
            Configuration configuration = new Configuration();
            configuration.directoryList = this.directoryList;
            configuration.exclusionList = this.exclusionList;
            return configuration;
        }

    }

    public List<Path> getDirectoryList() {
        return directoryList;
    }

    public List<Path> getExclusionList() {
        return exclusionList;
    }

    public List<Path> getFileExtension() {
        return fileExtension;
    }

}
