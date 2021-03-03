package com.weather.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.demo.dto.weather.ForecastResponse;
import com.weather.demo.service.WeatherService;

import static com.weather.demo.util.TestUtil.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WeatherController.class, WeatherService.class})
class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private WeatherController weatherController;

    @Test
    void getWeatherDetails() throws IOException {
        String cityName = "Nellore";
        Mockito.when(weatherService.getWeather(cityName))
                .thenReturn(getObjectMapper()
                        .readValue(readFile("forecastResponse.json"), new TypeReference<>() {
                        }));
        ResponseEntity<ForecastResponse> weatherDetails = weatherController.getWeatherDetails(cityName);
        assertNotNull(weatherDetails.getBody());
        assertTrue(weatherDetails.getBody().getDayWeatherList().size() == 3);
    }
}