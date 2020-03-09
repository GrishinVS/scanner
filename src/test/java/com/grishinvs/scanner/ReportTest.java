package com.grishinvs.scanner;

import com.grishinvs.scanner.report.FileReport;
import com.grishinvs.scanner.report.ReportHandler;
import com.grishinvs.scanner.report.ReportHandlerImpl;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReportTest {

    private static final String DIRECTORY_NAME = "testDir";
    private static final String FILE_PATH_1 = DIRECTORY_NAME + "/" + "fileName1";
    private static final String FILE_PATH_2 = DIRECTORY_NAME + "/" + "fileName2";
    private static ReportHandler reportHandler;
    private static String expectedReport;

    @BeforeClass
    public static void setUpClass() throws IOException {
        reportHandler = new ReportHandlerImpl();
        Files.createDirectory(Paths.get(DIRECTORY_NAME));
        Files.createFile(Paths.get(FILE_PATH_1));
        Files.createFile(Paths.get(FILE_PATH_2));
        initializeExpectedReport();
    }

    private static void initializeExpectedReport() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        expectedReport = String.join(System.lineSeparator(), "[", "file = " + Paths.get(FILE_PATH_1).toAbsolutePath(),
                "date = " + date, "size = 0][", "file = " + Paths.get(FILE_PATH_2).toAbsolutePath(), "date = " + date, "size = 0]");
    }

    @AfterClass
    public static void tearDownAfterClass() throws IOException {
        Files.delete(Paths.get(FILE_PATH_1));
        Files.delete(Paths.get(FILE_PATH_2));
        Files.delete(Paths.get(DIRECTORY_NAME));
    }

    @Test
    public void testCreateReport() throws IOException {
        File file1 = new File(FILE_PATH_1);
        File file2 = new File(FILE_PATH_2);
        FileReport fileReport1 = new FileReport(file1.getAbsolutePath(), file1.lastModified(), file1.length());
        FileReport fileReport2 = new FileReport(file1.getAbsolutePath(), file1.lastModified(), file1.length());
        List<File> fileList = List.of(new File[] {file1, file2});
        Assert.assertEquals(expectedReport, reportHandler.createReport(fileList));
    }

    @Test
    public void testSaveReport() throws IOException {
        final Path filePath = Paths.get(DIRECTORY_NAME + "/" + "testReport.txt");
        reportHandler.saveReport(expectedReport, filePath);
        Assert.assertEquals(expectedReport, Files.readString(filePath));
        Files.delete(filePath);
    }

}
