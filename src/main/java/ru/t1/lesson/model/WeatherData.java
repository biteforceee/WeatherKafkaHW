package ru.t1.lesson.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WeatherData {
    private Integer temperature;
    private State state;
    private City city;
    private LocalDate date;
}
