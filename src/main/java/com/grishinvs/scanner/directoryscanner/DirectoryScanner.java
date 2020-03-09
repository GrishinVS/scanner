package com.grishinvs.scanner.directoryscanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.io.File;
import java.util.List;

public interface DirectoryScanner {

    List<File> scan(Configuration configuration);

}