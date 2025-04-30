package org.example;

import org.example.Board;
import org.example.Tile;
import org.example.TileBag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Player extends Thread {
    private final String name;
    private final TileBag tileBag;
    private final Board board;
    private final Set<String> dictionary;
    private final List<Tile> tiles = new ArrayList<>();
    int score = 0;

    Player(String name, TileBag tileBag, Board board, Set<String> dictionary) {
        super(name);
        this.name = name;
        this.tileBag = tileBag;
        this.board = board;
        this.dictionary = dictionary;
    }

    public void run() {
        tiles.addAll(tileBag.drawTiles(7));

        while (!tileBag.isEmpty()) {
            String word = findWord();

            if (word != null) {
                int wordPoints = calculatePoints(word);
                score += wordPoints;
                board.submitWord(name, word, wordPoints);
                removeUsedTiles(word);
                tiles.addAll(tileBag.drawTiles(word.length()));
            } else {
                tiles.clear();
                tiles.addAll(tileBag.drawTiles(7));
            }
        }
    }

    private String findWord() {
        List<Character> tileLetters = new ArrayList<>();
        for (Tile tile : tiles) {
            tileLetters.add(Character.toLowerCase(tile.letter));
        }

        for (String word : dictionary) {
            if (canFormWord(word, new ArrayList<>(tileLetters))) {
                return word;
            }
        }
        return null;
    }

    private boolean canFormWord(String word, List<Character> available) {
        for (char c : word.toCharArray()) {
            if (!available.remove((Character) c)) {
                return false;
            }
        }
        return true;
    }

    private int calculatePoints(String word) {
        int sum = 0;
        List<Tile> used = new ArrayList<>();
        for (char c : word.toCharArray()) {
            for (Tile tile : tiles) {
                if (tile.letter == Character.toUpperCase(c) && !used.contains(tile)) {
                    sum += tile.points;
                    used.add(tile);
                    break;
                }
            }
        }
        return sum;
    }

    private void removeUsedTiles(String word) {
        for (char c : word.toCharArray()) {
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).letter == Character.toUpperCase(c)) {
                    tiles.remove(i);
                    break;
                }
            }
        }
    }
}
