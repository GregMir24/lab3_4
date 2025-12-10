package utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGen {
    private static final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    public static String generateId(String objType){
        AtomicInteger counter = counters.computeIfAbsent(objType, k -> new AtomicInteger(1));
        int number = counter.getAndIncrement();
        return objType.toUpperCase() + "-" + number;
    }
}
