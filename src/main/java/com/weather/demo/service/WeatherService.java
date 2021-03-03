package com.weather.demo.service;

import com.weather.demo.dto.open.weather.ListItem;
import com.weather.demo.dto.open.weather.WeatherResponse;
import com.weather.demo.dto.weather.DayWeather;
import com.weather.demo.dto.weather.ForecastResponse;
import com.weather.demo.errors.InternalServerError;
import com.weather.demo.errors.NotFoundException;
import com.weather.demo.proxy.OpenWeatherProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WeatherService {

    public static final String METRIC = "metric";
    @Autowired
    private OpenWeatherProxy openWeatherProxy;

    @Value("${openweather.appid}")
    private String appId;

    public ForecastResponse getWeather(String cityName) {
        WeatherResponse weatherResponse = openWeatherProxy.getWeather(cityName, METRIC, appId);
        log.info("Weather Response : {}", weatherResponse);
        if (Objects.nonNull(weatherResponse) && Objects.nonNull(weatherResponse.getCod()) && weatherResponse.getCod().equalsIgnoreCase("200") && Objects.nonNull(weatherResponse.getList()) && !weatherResponse.getList().isEmpty()) {
            return buildForecastWithMinMaxTemperatures(cityName, weatherResponse.getList());
        } else if (Objects.nonNull(weatherResponse) && Objects.nonNull(weatherResponse.getCod()) && weatherResponse.getCod().equalsIgnoreCase("404")) {
            log.error("City Not Found - {} ", cityName);
            throw new NotFoundException("City Not Found");
        } else {
            log.error("Error Retrieving  response from external System ");
            throw new InternalServerError("Unable to Parse Response From Downstream");
        }
    }

    private ForecastResponse buildForecastWithMinMaxTemperatures(String cityName, List<ListItem> list) {
        Collection<DayWeather> dayWeatherList = getNext3DayWeatherForecast(list);
        return ForecastResponse.builder().cityName(cityName).dayWeatherList(dayWeatherList).build();
    }

    private Collection<DayWeather> getNext3DayWeatherForecast(List<ListItem> list) {
        return list.stream().filter(listItem ->
                listItem.getLocalDateTime().isBefore(LocalDate.now().plusDays(3).atTime(23, 59, 59))
                        && listItem.getLocalDateTime().isAfter(LocalDate.now().atTime(23, 59, 59)))
                .map(listItem -> {
                    List<String> suggestions = new ArrayList<>();
                    if (listItem.getRain() != null) {
                        suggestions.add("Carry Umbrella");
                    }
                    if (listItem.getSnow() != null) {
                        suggestions.add("Wear Sweater");
                    }
                    if (listItem.getMain().getTempMax().compareTo(BigDecimal.valueOf(40)) == 1) {
                        suggestions.add("Use Sunscreen Lotion");
                    }

                    return DayWeather.builder().date(listItem.getLocalDateTime().toLocalDate())
                            .suggestions(suggestions.isEmpty() ? null : suggestions)
                            .maximumTemperature(Objects.nonNull(listItem.getMain()) ? listItem.getMain().getTempMax() : new BigDecimal(0))
                            .minimumTemperature(Objects.nonNull(listItem.getMain()) ? listItem.getMain().getTempMin() : new BigDecimal(0)).build();
                })
                .collect(Collectors.toMap(dayWeather -> dayWeather.getDate(), dayWeather -> dayWeather, (dayWeather, dayWeather2) -> {
                    TreeSet<BigDecimal> maxMin = new TreeSet<>();
                    maxMin.addAll(List.of(dayWeather.getMinimumTemperature(), dayWeather2.getMinimumTemperature(),
                            dayWeather.getMaximumTemperature(), dayWeather2.getMaximumTemperature()));
                    dayWeather.setMinimumTemperature(maxMin.first());
                    dayWeather.setMaximumTemperature(maxMin.last());
                    return dayWeather;
                })).values();
    }


}
