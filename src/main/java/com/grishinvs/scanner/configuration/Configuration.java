package com.grishinvs.scanner.configuration;

import java.util.List;

/**
 * Конфигурационный класс. Содержит параметры программы.
 */
public class Configuration {

    /**
     * Каталоги, подлежащие сканированию
     */
    private List<String> directoryList;

    /**
     * Каталоги, не подлежащие сканированию
     */
    private List<String> exclusionList;

    /**
     * Расширения файлов, подлежаще сканированию
     */
    private List<String> fileExtension;

    public static class Builder {

        private List<String> directoryList;
        private List<String> fileExtension;
        private List<String> exclusionList;

        public Builder setDirectoryList(List<String> directoryList) {
            this.directoryList = directoryList;
            return this;
        }

        public Builder setExclusionList(List<String> exclusionList) {
            this.exclusionList = exclusionList;
            return this;
        }

        public Builder setFileExtension(List<String> fileExtension) {
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
    public List<String> getDirectoryList() {
        return directoryList;
    }

    public List<String> getExclusionList() {
        return exclusionList;
    }

    public List<String> getFileExtension() {
        return fileExtension;
    }

}
