package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Board {
    private final Lock lock = new ReentrantLock();
    private final List<String> history = new ArrayList<>();

    void submitWord(String player, String word, int points) {
        lock.lock();
        try {
            history.add(player + " played '" + word + "' for " + points + " points.");
            System.out.println(player + " played '" + word + "' for " + points + " points.");
        } finally {
            lock.unlock();
        }
    }
}