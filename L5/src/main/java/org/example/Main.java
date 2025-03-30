package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ImageRepository repository = new ImageRepository();

        repository.addImage(new ImageItem("Desktop",
                LocalDate.of(2025, 3, 26),
                List.of("dog", "hera"), "/Users/beatricefilote/Desktop/PA/L5/images/image1.jpg"));
        repository.displayImage(repository.getImages().get(0));
    }
}