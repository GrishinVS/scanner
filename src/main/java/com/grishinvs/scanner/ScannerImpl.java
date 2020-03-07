package com.grishinvs.scanner;

import com.grishinvs.scanner.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScannerImpl implements Scanner {

    private int counter;

    @Override
    public List<ScanFile> scan(Configuration configuration) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<List<ScanFile>> task = new ScanTask(configuration);
        Future<List<ScanFile>> future = executor.submit(task);
        try {
            while (!future.isDone()) {
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
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private boolean isMinute() {
        return counter == 6;
    }

}
