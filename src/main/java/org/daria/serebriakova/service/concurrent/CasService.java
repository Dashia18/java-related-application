package org.daria.serebriakova.service.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class CasService {
    private AtomicInteger value = new AtomicInteger(0);

    public boolean compareAndSet(int expectedValue, int newValue) {
        return value.compareAndSet(expectedValue, newValue);
    }

    public int getValue() {
        return value.get();
    }
}
