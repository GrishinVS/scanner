package com.grishinvs.scanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Конфигурационный класс. Содержит параметры программы.
 */
public class Configuration {

    /**
     * Каталоги, подлежащие сканированию
     */
    private static List<String> directoryList;

    /**
     * Каталоги, не подлежащие сканированию
     */
    private static List<String> exclusionList;

    static {
        final String PROPERTIES_FILE_PATH = "config.properties";
        Properties properties = new Properties();
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(inputStream);
            directoryList = Arrays.stream(properties.getProperty("directories").split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            exclusionList = Arrays.stream(properties.getProperty("excludesDirectories").split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Configuration() {
    }

    public static List<String> getDirectoryList() {
        return directoryList;
    }

    public static List<String> getExclusionList() {
        return exclusionList;
    }

}
