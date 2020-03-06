package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationLoader;
import com.grishinvs.scanner.configuration.PropertiesConfigurationLoader;
import com.grishinvs.scanner.storing.Saver;
import com.grishinvs.scanner.storing.ScanFileSaver;

import java.util.List;

//TODO Реализовать чтение/запись объектов в текстовый файл (возможно через рефлексию),
// доделать обход каталога.
public class Runner {

    public static void main(String[] args) {
        // Получение конфигурации
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
        Configuration configuration = configurationLoader.loadConfiguration();
        // Получение списка просканированных файлов
        Scanner scanner = new ScannerImpl();
        List<ScanFile> scanFileList = scanner.scan(configuration);
        // Запись результата в файл
        Saver<ScanFile> saver = new ScanFileSaver();
        saver.save(scanFileList, "чудо путь");
    }

}
