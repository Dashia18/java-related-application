package org.daria.serebriakova.service.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.stereotype.Service;

@Service
public class AtomicService {
    private final AtomicInteger atomicCount = new AtomicInteger(0);
    private AtomicReference<String> reference = new AtomicReference<>("initial");


    // Thread-safe increment using atomic operation
    public void increment() {
        atomicCount.incrementAndGet();
    }

    public int getAtomicCount() {
        return atomicCount.get();
    }

    public void update(String newValue) {
        reference.set(newValue);
    }

    public String get() {
        return reference.get();
    }
}
