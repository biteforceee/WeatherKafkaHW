package ru.t1.lesson.model;

import java.util.Random;

public enum State {
    SUNNY,
    CLOUDY,
    RAINY;

    private static final Random RANDOM = new Random();

    public static State randomState() {
        State[] states = values();
        return states[RANDOM.nextInt(states.length)];
    }
}
