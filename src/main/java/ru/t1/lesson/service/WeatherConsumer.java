package ru.t1.lesson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.t1.lesson.model.WeatherData;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "weather", groupId = "weather-group")
    public void listen(String message) throws JsonProcessingException {
        log.info("Message received, time: {}", LocalDateTime.now());
        System.out.println("Received: " + message);
        WeatherData weatherData = objectMapper.readValue(message, WeatherData.class);
        WeatherStatisticsService.add(weatherData);
        System.out.println(WeatherStatisticsService.getStatistics(weatherData.getCity()));
    }
}
