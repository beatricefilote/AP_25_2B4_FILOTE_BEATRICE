package org.example;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Main {
    public static void main(String[] args) throws InterruptedException {
        TileBag bag = new TileBag();
        Board board = new Board();

        Set<String> dictionary = new HashSet<>(Arrays.asList(
                "sun", "moon", "star", "planet", "galaxy", "comet", "asteroid", "orbit", "space", "rocket"
        ));

        List<Player> players = Arrays.asList(
                new Player("Bea", bag, board, dictionary),
                new Player("Damian", bag, board, dictionary),
                new Player("Antonia", bag, board, dictionary)
        );

        for (Player player : players) {
            player.setName(player.getName());
            player.start();
        }

        for (Player player : players) {
            player.join();
        }

        System.out.println("\nGame Over! Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.score);
        }

        Player winner = Collections.max(players, Comparator.comparingInt(p -> p.score));
        System.out.println("\nWinner: " + winner.getName() + " 🎉");
    }
}