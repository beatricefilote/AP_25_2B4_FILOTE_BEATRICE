package org.example;

public class Tile {
    char letter;
    int points;

    Tile(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public String toString() {
        return letter + "(" + points + ")";
    }
}