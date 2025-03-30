package org.example;

import java.time.LocalDate;
import java.util.List;
public record ImageItem(String name, String path) {
    public ImageItem(String desktop, LocalDate date, List<String> example, String s) {
        this(desktop, s);
    }
}