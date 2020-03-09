package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.directoryscanner.DirectoryScanner;
import com.grishinvs.scanner.directoryscanner.DirectoryScannerImpl;
import com.grishinvs.scanner.report.ReportHandler;
import com.grishinvs.scanner.report.ReportHandlerImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportTest {

    private static DirectoryScanner directoryScanner;
    private static ReportHandler reportHandler;
    private static Configuration configuration;

    @BeforeClass
    public static void setUpClass() {
        directoryScanner = new DirectoryScannerImpl();
        reportHandler = new ReportHandlerImpl();
        Configuration configuration = new Configuration();
    }

    @Test
    public void testCreateReport() {

    }

    @Test
    public void testSaveReport() {

    }

}
