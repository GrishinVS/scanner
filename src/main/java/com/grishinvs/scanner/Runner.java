package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationLoader;
import com.grishinvs.scanner.configuration.ConfigurationUtils;
import com.grishinvs.scanner.configuration.PropertiesConfigurationLoader;

import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        // Получение конфигурации
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
        Configuration configuration = configurationLoader.loadConfiguration();
        ConfigurationUtils.initializeDirectoryPaths(configuration, Arrays.asList(args),"-");
        // Получение списка просканированных файлов
        DirectoryScanner scanner = new DirectoryScannerImpl();
        List<ScanFile> scanFileList = scanner.scan(configuration);
        scanFileList.forEach(System.out::println);
        // Запись результата в файл
    }

}
