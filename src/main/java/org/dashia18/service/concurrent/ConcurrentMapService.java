package org.dashia18.service.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class ConcurrentMapService {

    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    // Thread-safe put operation
    public void put(String key, Integer value) {
        map.put(key, value);
    }

    // Thread-safe get operation
    public Integer get(String key) {
        return map.get(key);
    }
}
