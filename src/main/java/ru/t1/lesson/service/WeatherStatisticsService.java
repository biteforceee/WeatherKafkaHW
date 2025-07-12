package ru.t1.lesson.service;

import ru.t1.lesson.model.City;
import ru.t1.lesson.model.State;
import ru.t1.lesson.model.WeatherData;

import java.util.*;

public class WeatherStatisticsService {
    private static final List<WeatherData> weatherList = new ArrayList<>();

    public static void add(WeatherData weatherData) {
        weatherList.add(weatherData);
    };

    public static WeatherData findHottestDay(City city) {
        return weatherList.stream()
                .filter(data -> data.getCity().equals(city))
                .max(Comparator.comparing(WeatherData::getTemperature))
                .get();
    };

    public static long findRainyDays(City city) {
        return weatherList.stream()
                .filter(data -> data.getCity().equals(city))
                .filter(data -> data.getState().equals(State.RAINY))
                .count();
    }

    public static double findMeanTemp(City city) {
        return weatherList.stream()
                .filter(data -> data.getCity().equals(city))
                .mapToInt(WeatherData::getTemperature)
                .average()
                .orElse(0.);
    }

    public static String getStatistics(City city) {
        WeatherData hottestDay = findHottestDay(city);
        long rainyDays = findRainyDays(city);
        double meanTemp = findMeanTemp(city);
        String stat = "\nСтатистика по городу: " + city
                + "\nСамый жаркий день: " + hottestDay.getDate() + " , температура: " + hottestDay.getTemperature()
                + "\nКоличество дождливых дней: " + rainyDays
                + "\nСредняя температура: " + meanTemp;
        return stat;
    }
}
