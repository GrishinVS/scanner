package com.grishinvs.scanner.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Загружает конфигурацию из конфигурационного файла
public class PropertiesConfigurationLoader implements ConfigurationLoader {

    /**
     * Имя конфигурационного файла
     */
    private String propertiesFilePath = "config.properties";

    /**
     * Разделитель элементов списка в конфигурационном файле
     */
    private String delimiter = ",";

    public PropertiesConfigurationLoader() {
    }

    public PropertiesConfigurationLoader(String propertiesFilePath, String delimiter) {
        this.propertiesFilePath = propertiesFilePath;
        this.delimiter = delimiter;
    }

    /**
     * Загружает конфигурацию из конфигурационного файла
     *
     * @return конфигурация
     */
    public Configuration loadConfiguration() {
        Properties properties = new Properties();
        Configuration configuration = new Configuration();
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
                return new Configuration.Builder()
                        .setDirectoryList(Arrays.stream(properties.getProperty("directories").split(delimiter))
                                .map(String::trim)
                                .filter(Predicate.not(String::isBlank))
                                .map(Paths::get)
                                .collect(Collectors.toList()))
                        .setExclusionList(Arrays.stream(properties.getProperty("excludesDirectories").split(delimiter))
                                .map(String::trim)
                                .filter(Predicate.not(String::isBlank))
                                .map(Paths::get)
                                .collect(Collectors.toList()))
                        .setExcludesExtensions(Arrays.stream(properties.getProperty("excludesExtensions").split(delimiter))
                                .map(String::trim)
                                .filter(Predicate.not(String::isBlank))
                                .collect(Collectors.toList()))
                        .setThreadNumber(Integer.parseInt(properties.getProperty("threadNumber")))
                        .build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return new Configuration();
    }

    public String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    public void setPropertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

}
