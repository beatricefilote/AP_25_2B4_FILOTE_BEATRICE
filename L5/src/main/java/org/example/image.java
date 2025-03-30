package org.example;

import java.time.LocalDate;
import java.util.List;
public record image(String name, LocalDate date, List<String> tags, String path) {

}