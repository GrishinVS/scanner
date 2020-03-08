package com.grishinvs.scanner.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfigurationUtils {

    public static void initializeDirectoryPaths(Configuration configuration, List<String> paths, String delimiter) {
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

    private static List<Path> convertStringToPath(List<String> pathList) {
        return pathList.stream()
                .map(String::trim)
                .filter(Predicate.not(String::isBlank))
                .map(Paths::get)
                .collect(Collectors.toList());
    }

}
