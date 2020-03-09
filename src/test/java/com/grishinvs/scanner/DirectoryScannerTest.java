package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.directoryscanner.DirectoryScanner;
import com.grishinvs.scanner.directoryscanner.DirectoryScannerImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScannerTest {

    private static DirectoryScanner directoryScanner;
    private static Configuration configuration;
    private static final Path ROOT_PATH = Paths.get("src/test");

    @BeforeClass
    public static void setUpClass() {
        directoryScanner = new DirectoryScannerImpl();
        configuration = new Configuration();
        configuration.setDirectoryList(List.of(ROOT_PATH));
    }

    @Test
    public void testScan() throws IOException {
        List<File> expectedFileList = new ArrayList<>();
        Files.walkFileTree(ROOT_PATH, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                expectedFileList.add(new File(String.valueOf(file)));
                return FileVisitResult.CONTINUE;
            }
        });
        List<File> actualFileList = directoryScanner.scan(configuration);
        Assert.assertEquals(expectedFileList, actualFileList);
    }

}
