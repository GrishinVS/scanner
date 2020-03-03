package com.grishinvs.scanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

// Загружает конфигурацию из конфигурационного файла
public class ConfigurationLoader {

    /**
     * Имя конфигурационного файла
     */
    private static final String PROPERTIES_FILE_PATH = "config.properties";

    /**
     * Разделитель строк в конфигурационном файле
     */
    private static final String DELIMITER = ",";

    /**
     * Загружает конфигурацию из конфигурационного файла
     *
     * @return конфигурация
     */
    public static Configuration loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
            if (inputStream != null) {
                properties.load(inputStream);
                return new Configuration.Builder()
                        .setDirectoryList(Arrays.stream(properties.getProperty("directories").split(DELIMITER))
                                .map(String::trim)
                                .collect(Collectors.toList()))
                        .setExclusionList(Arrays.stream(properties.getProperty("excludesDirectories").split(DELIMITER))
                                .map(String::trim)
                                .collect(Collectors.toList()))
                        .build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return new Configuration();
    }

}
