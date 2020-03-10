package com.grishinvs.scanner.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Утилитный класс для настройки конфигурации.
 */
public class ConfigurationUtils {

    /**
     * Символ, разделяющий каталоги для сканирования и исключения
     */
    private static final String delimiter = "-";

    /**
     * Заполняет конфигурацю списком каталогов для сканирования и списком каталогов-исключений.
     *
     * @param configuration конфигурация
     * @param paths         пути каталогов
     */
    public static void initializeDirectoryPaths(Configuration configuration, List<String> paths) {
        if (paths != null && !paths.isEmpty()) {
            int delimiterIndex = paths.indexOf(delimiter);
            if (delimiterIndex != -1) {
                configuration.setDirectoryList(convertStringToPath(paths.subList(0, delimiterIndex)));
                configuration.setExclusionDirectoryList(convertStringToPath(paths.subList(delimiterIndex + 1, paths.size())));
            } else {
                configuration.setDirectoryList(convertStringToPath(paths));
            }
        }
    }

    /**
     * Конвертирует список путей типа {@link java.lang.String}  в список путей типа {@link java.nio.file.Path}.
     *
     * @param pathList список путей
     * @return список путей
     */
    private static List<Path> convertStringToPath(List<String> pathList) {
        return pathList.stream()
                .filter(Predicate.not(String::isBlank))
                .map(String::trim)
                .map(Paths::get)
                .collect(Collectors.toList());
    }

}
