package org.daria.serebriakova.service.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.springframework.stereotype.Service;

/*
 * A ReadWriteLock allows multiple threads to read a resource concurrently but only one thread to write.
 * This can improve performance in read-heavy scenarios.
 *
 * A thread holding the write lock can acquire the read lock without releasing the write lock first.
 * This is called lock downgrading.
 *
 * A thread holding the read lock cannot acquire the write lock without releasing the read lock first.
 * This is called lock upgrading and is not directly supported to avoid potential deadlocks.
 *
 * Use Cases
 * Caching: Multiple threads reading from a cache while only one thread updates it.
 * Configuration Management: Multiple threads reading configuration settings while only one thread updates them.
 * Statistics Collection: Multiple threads reading statistics while only one thread updates them.
 */
@Service
public class ReadWriteLockService {
    private final List<String> resource = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /*
    * Lock readLock(): Returns the lock used for reading.
    */
    private final Lock readLock = lock.readLock();
    /*
    * Lock writeLock(): Returns the lock used for writing.
    */
    private final Lock writeLock = lock.writeLock();

    // Method to read the resource
    public List<String> readResource() {
        readLock.lock();
        try {
            // Simulate some read delay
            Thread.sleep(100);
            return new ArrayList<>(resource);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        } finally {
            readLock.unlock();
        }
    }

    // Method to write to the resource
    public void writeResource(String value) {
        writeLock.lock();
        try {
            // Simulate some write delay
            Thread.sleep(100);
            resource.add(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}
