package org.example;

import java.util.*;
import java.util.stream.Collectors;

class Location implements Comparable<Location> {
    public Location(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        friendly,
        neutral,
        enemy
    }

    private String name;
    private Type type;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Location[] locs = new Location[5];
        locs[0] = new Location("Iasi", Location.Type.friendly);
        locs[1] = new Location("Italia", Location.Type.neutral);
        locs[2] = new Location("Ucraina", Location.Type.enemy);
        locs[3] = new Location("Rusia", Location.Type.enemy);
        locs[4] = new Location("Bucuresti", Location.Type.friendly);

        TreeSet<Location> friendlyLocations = Arrays.stream(locs)
                .filter(loc -> loc.getType() == Location.Type.friendly)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly Locations (sorted by natural order):");
        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locs)
                .filter(loc -> loc.getType() == Location.Type.enemy)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Enemy Locations (sorted by type and name):");
        enemyLocations.forEach(System.out::println);


    }

}