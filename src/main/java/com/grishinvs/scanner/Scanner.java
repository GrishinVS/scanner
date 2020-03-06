package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.util.List;

public interface Scanner {

    List<ScanFile> scan(Configuration configuration);

}
