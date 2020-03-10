package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;
import com.grishinvs.scanner.configuration.ConfigurationUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationUtilsTest {

    private static Configuration configuration;

    @BeforeClass
    public static void setUpClass() {
        configuration = new Configuration();
    }

    @Test
    public void testInitializeDirectoryPaths() {
        String directoryPath = "src/main/java/com/grishinvs/scanner";
        String exclusionDirectoryPath1 = "src/main/java/com/grishinvs/scanner/configuration";
        String exclusionDirectoryPath2 = "src/main/java/com/grishinvs/scanner/report";
        List<String> args = List.of(new String[]{ directoryPath, "-",
                exclusionDirectoryPath1, exclusionDirectoryPath2});
        List<Path> expectedDirectoryList = new ArrayList<>();
        expectedDirectoryList.add(Paths.get(directoryPath));
        List<Path> expectedExclusionDirectoryList = List.of(new Path[]{Paths.get(exclusionDirectoryPath1),
                Paths.get(exclusionDirectoryPath2)});
        ConfigurationUtils.initializeDirectoryPaths(configuration, args);
        Assert.assertEquals(expectedDirectoryList, configuration.getDirectoryList());
        Assert.assertEquals(expectedExclusionDirectoryList, configuration.getExclusionDirectoryList());
    }

}
