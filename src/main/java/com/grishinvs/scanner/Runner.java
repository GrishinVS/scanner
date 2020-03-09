package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationLoader;
import com.grishinvs.scanner.configuration.ConfigurationUtils;
import com.grishinvs.scanner.configuration.PropertiesConfigurationLoader;
import com.grishinvs.scanner.directoryscanner.DirectoryScanner;
import com.grishinvs.scanner.directoryscanner.DirectoryScannerImpl;
import com.grishinvs.scanner.report.ReportHandler;
import com.grishinvs.scanner.report.ReportHandlerImpl;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        // Получение конфигурации
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
        Configuration configuration = configurationLoader.loadConfiguration();
        ConfigurationUtils.initializeDirectoryPaths(configuration, Arrays.asList(args), "-");
        // Получение списка просканированных файлов
        DirectoryScanner scanner = new DirectoryScannerImpl();
        List<File> scanFileList = scanner.scan(configuration);
        // Сохранение результата
        ReportHandler reportHandler = new ReportHandlerImpl();
        String report = reportHandler.createReport(scanFileList);
        reportHandler.saveReport(report, Paths.get("C:/Users/Vdm/Desktop/sbt_task/report.txt"));
    }

}
