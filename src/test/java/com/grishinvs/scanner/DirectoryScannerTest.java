package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.directoryscanner.DirectoryScanner;
import com.grishinvs.scanner.directoryscanner.DirectoryScannerImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class DirectoryScannerTest {

    private static DirectoryScanner directoryScanner;
    private static Configuration configuration;

    @BeforeClass
    public static void setUpClass() {
        directoryScanner = new DirectoryScannerImpl();
        Configuration configuration = new Configuration();
    }

    @Test
    public void testScan() {

    }

}
