package org.daria.serebriakova.service.concurrent;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcurrentService {
    private final ReentrantLockService reentrantLockService;

    public List<String> getThreasRusLogs() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Create and start database access threads
        executorService.submit(new ReentrantLockService.DatabaseAccess(reentrantLockService, "Thread 1"));
        executorService.submit(new ReentrantLockService.DatabaseAccess(reentrantLockService, "Thread 2"));
        executorService.submit(new ReentrantLockService.DatabaseAccess(reentrantLockService, "Thread 3"));
        // Run for a while and then shut down
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executorService.shutdownNow();

        return reentrantLockService.readResource();
    }
}
