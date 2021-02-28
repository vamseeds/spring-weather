package com.weather.demo.dto.weather;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
public class ForecastResponse {
    private String cityName;
    private Collection<DayWeather> dayWeatherList;
}
