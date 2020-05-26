package org.codewars.threaded;


import java.util.*;

public class Counter {
    private final List<Integer> numbers = new ArrayList<>();
    private final Map<Integer, Long> threads = new HashMap<>();
    private final Map<Long, List<Integer>> threadNumbers = new HashMap<>();

    public synchronized void count(int i) {
        long threadId = Thread.currentThread().getId();
        numbers.add(i);
        threads.put(i, threadId);

        List<Integer> threadList = threadNumbers.get(threadId);
        if (threadList == null) {
            threadList = new ArrayList<>();
            threadNumbers.put(threadId, threadList);
        }
        threadList.add(i);
        System.out.printf("Printing %s in thread %s", i, threadId);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public Set<Long> getThreadIds() {
        return new HashSet<>(threads.values());
    }

    public List<Integer> getNumbersInSameThreadAs(int i) {
        long threadId = threads.get(i);
        return new ArrayList<>(threadNumbers.get(threadId));
    }
}