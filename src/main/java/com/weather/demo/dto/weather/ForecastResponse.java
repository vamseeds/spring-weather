package com.weather.demo.dto.weather;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponse {
    private String cityName;
    private Collection<DayWeather> dayWeatherList;
}
