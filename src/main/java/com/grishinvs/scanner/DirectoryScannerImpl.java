package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryScannerImpl implements DirectoryScanner {

    private int counter;

    @Override
    public List<File> scan(Configuration configuration) {
        ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadNumber());
        List<ScanTask> scanTaskList = configuration.getDirectoryList().stream()
                .filter(Predicate.not(configuration.getExclusionDirectoryList()::contains))
                .map(item -> new ScanTask(configuration, item))
                .collect(Collectors.toList());
        try {
            List<Future<List<File>>> futureList = executor.invokeAll(scanTaskList);
            while (futureList.stream().anyMatch(Predicate.not(Future::isDone))) {
                Thread.sleep(TimeUnit.SECONDS.toMillis(6));
                if (!isMinute()) {
                    System.out.println(".");
                } else {
                    System.out.println("|");
                    counter = 0;
                }
                counter++;
            }
            executor.shutdown();
            return collectResult(futureList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<File> collectResult(List<Future<List<File>>> futureList)
            throws ExecutionException, InterruptedException {
        List<File> result = new ArrayList<>();
        for (Future<List<File>> future : futureList) {
            result.addAll(future.get());
        }
        return result;
    }

    private boolean isMinute() {
        return counter == 6;
    }

}
