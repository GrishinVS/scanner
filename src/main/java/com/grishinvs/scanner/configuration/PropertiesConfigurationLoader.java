package com.grishinvs.scanner.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Реализация загрузчика конфигурации из properties файла.
 */
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
     * Удаляет из потока пустые строки и пробельные символы в начале и конце строки.
     *
     * @param stringStream поток строк
     * @return поток строк
     */
    private static Stream<String> preprocessStringStream(Stream<String> stringStream) {
        return stringStream
                .filter(Predicate.not(String::isBlank))
                .map(String::strip);
    }

    /**
     * Загружает конфигурацию из конфигурационного файла
     *
     * @return конфигурация
     */
    public Configuration loadConfiguration() {
        Properties properties = new Properties();
        Configuration configuration = null;
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
                configuration = new Configuration.Builder()
                        .setExcludesExtensions(
                                preprocessStringStream(Arrays.stream(properties.getProperty("excludesExtensions").split(delimiter)))
                                        .collect(Collectors.toList()))
                        .setThreadNumber(Integer.parseInt(properties.getProperty("threadNumber")))
                        .build();
                ConfigurationUtils.initializeDirectoryPaths(configuration,
                        preprocessStringStream(Arrays.stream(properties.getProperty("directories").split(delimiter)))
                                .collect(Collectors.toList()));
                return configuration;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
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
