package com.grishinvs.scanner.directoryscanner;

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

/**
 * Реализация сканнера каталогов
 */
public class DirectoryScannerImpl implements DirectoryScanner {

    /**
     * Счетчик цикла сканирования. Используется для отсчета минуты.
     */
    private int counter;

    @Override
    public List<File> scan(Configuration configuration) {
        ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadNumber());
        List<ScanTask> scanTaskList = createScanTasks(configuration);
        try {
            counter = 0;
            List<Future<List<File>>> futureList = executor.invokeAll(scanTaskList);
            while (futureList.stream().anyMatch(Predicate.not(Future::isDone))) {
                Thread.sleep(TimeUnit.SECONDS.toMillis(6));
                showScanProcess();
            }
            executor.shutdown();
            return collectResult(futureList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Сопровождает процесс сканирования выводом в консоль каждые 6 секунд символа "."
     * и символа "|" каждую минуту.
     */
    private void showScanProcess() {
        if (!isMinute()) {
            System.out.println(".");
        } else {
            System.out.println("|");
            counter = 0;
        }
        counter++;
    }

    /**
     * Создает задачи сканирования.
     *
     * @param configuration конфигурация
     * @return список задача
     */
    private List<ScanTask> createScanTasks(Configuration configuration) {
        return configuration.getDirectoryList().stream()
                .filter(Predicate.not(configuration.getExclusionDirectoryList()::contains))
                .map(item -> new ScanTask(configuration, item))
                .collect(Collectors.toList());
    }

    /**
     * Собирает результаты выполнения потоков.
     *
     * @param futureList результаты выполнения потоков
     * @return список файлов
     */
    private List<File> collectResult(List<Future<List<File>>> futureList)
            throws ExecutionException, InterruptedException {
        List<File> result = new ArrayList<>();
        for (Future<List<File>> future : futureList) {
            result.addAll(future.get());
        }
        return result;
    }

    /**
     * Определяет, прошла ли минута
     *
     * @return true, если прошла иначе false
     */
    private boolean isMinute() {
        return counter == 6;
    }

}
