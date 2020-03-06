package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScannerImpl implements Scanner {

    @Override
    public List<ScanFile> scan(Configuration configuration) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new ScanTask(configuration));
        return null;
    }

}
