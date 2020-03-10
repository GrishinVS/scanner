package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationLoader;
import com.grishinvs.scanner.configuration.PropertiesConfigurationLoader;
import com.grishinvs.scanner.directoryscanner.DirectoryScanner;
import com.grishinvs.scanner.directoryscanner.DirectoryScannerImpl;
import com.grishinvs.scanner.report.ReportHandler;
import com.grishinvs.scanner.report.ReportHandlerImpl;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * Сканер каталогов.
 */
public class ScannerFacade {

    /**
     * Загрузчик конфигурации
     */
    private ConfigurationLoader configurationLoader;

    /**
     * Сканер каталогов
     */
    private DirectoryScanner directoryScanner;

    /**
     * Обработчик отчетов
     */
    private ReportHandler reportHandler;

    public ScannerFacade() {
        this.configurationLoader = new PropertiesConfigurationLoader();
        this.directoryScanner = new DirectoryScannerImpl();
        this.reportHandler = new ReportHandlerImpl();
    }

    /**
     * Инициирует сканирование. По завршению сканирования результат записывается в файл.
     *
     * @param reportPath путь к файлу, в который будет сохранен результат
     */
    public void startScan(String reportPath) {
        Configuration configuration = configurationLoader.loadConfiguration();
        List<File> fileList = directoryScanner.scan(configuration);
        String report = reportHandler.createReport(fileList);
        reportHandler.saveReport(report, Paths.get(reportPath));
    }

}
