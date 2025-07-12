package ru.t1.lesson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.t1.lesson.model.City;
import ru.t1.lesson.model.State;
import ru.t1.lesson.model.WeatherData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final Random random = new Random();
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendData() throws JsonProcessingException {
        String jsonFormat = objectMapper.writeValueAsString(generateWeatherData());
        log.info("Message sent, time: {}", LocalDateTime.now());
        kafkaTemplate.send("weather", jsonFormat);
    }

    private WeatherData generateWeatherData() {
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = toDate.minusDays(7);

        long startEpochDay = fromDate.toEpochDay();
        long endEpochDay = toDate.toEpochDay();
        long randomDay = startEpochDay + (long) (new Random().nextDouble() * (endEpochDay - startEpochDay));

        return new WeatherData(random.nextInt(36)
                ,State.randomState()
                ,City.randomCity()
                ,LocalDate.ofEpochDay(randomDay));
    }
}
