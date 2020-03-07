package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationLoader;
import com.grishinvs.scanner.configuration.PropertiesConfigurationLoader;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        // Получение конфигурации
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
        Configuration configuration = configurationLoader.loadConfiguration();
        // Получение списка просканированных файлов
        Scanner scanner = new ScannerImpl();
        List<ScanFile> scanFileList = scanner.scan(configuration);
        scanFileList.forEach(System.out::println);
        // Запись результата в файл
    }

}
