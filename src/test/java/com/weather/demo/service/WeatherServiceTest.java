package com.weather.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.weather.demo.dto.open.weather.WeatherResponse;
import com.weather.demo.dto.weather.ForecastResponse;
import com.weather.demo.errors.InternalServerError;
import com.weather.demo.errors.NotFoundException;
import com.weather.demo.proxy.OpenWeatherProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static com.weather.demo.util.TestUtil.getObjectMapper;
import static com.weather.demo.util.TestUtil.readFile;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WeatherService.class})
class WeatherServiceTest {

    @MockBean
    private OpenWeatherProxy openWeatherProxy;

    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeather() throws IOException {
        Mockito.when(openWeatherProxy.getWeather(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(getObjectMapper()
                        .readValue(readFile("openWeatherResponse.json"), new TypeReference<>() {
                        }));
        ForecastResponse nelloreForecastResponse = weatherService.getWeather("Nellore");
        assertEquals(3, nelloreForecastResponse.getDayWeatherList().size());
    }

    @Test
    void getWeatherErrorScenario() {
        Mockito.when(openWeatherProxy.getWeather(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        assertThrows(InternalServerError.class, () -> weatherService.getWeather("Nellore"));
    }


    @Test
    void getWeatherErrorScenarioWrongCity() {
        Mockito.when(openWeatherProxy.getWeather(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(WeatherResponse.builder().cod("404").build());
        assertThrows(NotFoundException.class, () -> weatherService.getWeather("XYZ"));
    }
}