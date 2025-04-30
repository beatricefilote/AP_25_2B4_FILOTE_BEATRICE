package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TileBag {
    private final List<Tile> tiles = new ArrayList<>();
    private final Random rand = new Random();
    private final Lock lock = new ReentrantLock();

    TileBag() {
        for (char c = 'A'; c <= 'Z'; c++) {
            int points = rand.nextInt(10) + 1;
            for (int i = 0; i < 10; i++) {
                tiles.add(new Tile(c, points));
            }
        }
        Collections.shuffle(tiles);
    }

    List<Tile> drawTiles(int count) {
        lock.lock();
        try {
            int toDraw = Math.min(count, tiles.size());
            List<Tile> drawn = new ArrayList<>(tiles.subList(0, toDraw));
            tiles.subList(0, toDraw).clear();
            return drawn;
        } finally {
            lock.unlock();
        }
    }

    boolean isEmpty() {
        lock.lock();
        try {
            return tiles.isEmpty();
        } finally {
            lock.unlock();
        }
    }
}
