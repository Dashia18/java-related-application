package org.dashia18.service.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * The ReentrantLock class is a commonly used explicit lock that can be acquired multiple times by the same thread.
 * A thread can acquire the lock it already holds without causing a deadlock.
 * You can create a fair lock, where the longest-waiting thread gets the lock first.
 * Provides methods like lock(), tryLock(), lockInterruptibly(), and unlock().
 */
@Slf4j
@Service
public class ReentrantLockService {
    private final List<String> resource = new ArrayList<>();
    private final Lock lock = new ReentrantLock();


    // Method to read the resource
    public List<String> readResource() {
        /*
         * Acquires the lock.
         * If the lock is not available, the current thread becomes disabled for thread scheduling purposes
         * and lies dormant until the lock has been acquired.
         */
        lock.lock();
        try {
            // Simulate some read delay
            Thread.sleep(100);
            return new ArrayList<>(resource);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        } finally {
            /*
             * Releases the lock.
             */
            lock.unlock();
        }
    }

    // Method to write to the resource
    public void writeResource(String value) {
        lock.lock();
        try {
            // Simulate some write delay
            Thread.sleep(100);
            resource.add(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    // Method to access the database
    public boolean accessDatabase(String threadName) {
        /*
         * Releases the lock.
         * Acquires the lock only if it is free at the time of invocation.
         */
        if (lock.tryLock()) {
            try {
                // Simulate some read delay
                Thread.sleep(100);
                String s = threadName + " acquired the lock and is accessing the database";
                log.info(s);
                writeResource(s);
            } catch (InterruptedException e) {
                String s = threadName + ": " + e.getMessage();
                log.error(s);
                writeResource(s);
                Thread.currentThread().interrupt();
            } finally {
                String s = threadName + " released the lock";
                log.info(s);
                writeResource(s);
                lock.unlock();
            }
        } else {
            /*
             * Useful when you want to try acquiring the lock without blocking if the lock is not available.
             */
            String s = threadName + " could not acquire read lock, performing other tasks";
            log.info(s);
            writeResource(s);
            performOtherTasks(threadName);
            return false;
        }
        return true;
    }

    // Simulate performing other tasks
    private void performOtherTasks(String threadName) {
        try {
            String s = threadName + " is performing other tasks";
            log.info(s);
            writeResource(s);
            Thread.sleep(100); // Simulate other tasks
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static class DatabaseAccess implements Runnable {
        private final ReentrantLockService sharedDatabase;
        private final String threadName;

        public DatabaseAccess(ReentrantLockService sharedDatabase, String threadName) {
            this.sharedDatabase = sharedDatabase;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            boolean isDone = false;
            while (!isDone) {
                isDone = sharedDatabase.accessDatabase(threadName);
                try {
                    Thread.sleep(300); // Simulate some delay between attempts
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
