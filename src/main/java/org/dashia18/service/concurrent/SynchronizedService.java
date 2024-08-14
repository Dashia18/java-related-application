package org.dashia18.service.concurrent;

import org.springframework.stereotype.Service;

@Service
public class SynchronizedService {

    private int count;

    public synchronized void incrementMethod() {
        // Critical section
        count++;
    }

    public void incrementBloch() {
        synchronized (this) {
            // Critical section
            count++;
        }
    }
}
