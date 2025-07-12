package ru.t1.lesson.model;

import java.util.Random;

public enum City {
    MOSCOW,
    TUMEN,
    SaintPetersburg;

    private static final Random RANDOM = new Random();

    public static City randomCity() {
        City[] cities = values();
        return cities[RANDOM.nextInt(cities.length)];
    }
}
