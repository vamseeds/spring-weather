package com.weather.demo.controller;

import com.weather.demo.dto.open.weather.WeatherResponse;
import com.weather.demo.dto.weather.ForecastResponse;
import com.weather.demo.errors.BadRequestException;
import com.weather.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public ResponseEntity<ForecastResponse> getWeatherDetails(@RequestParam(name = "cityName", required = true) String cityName) {
        if (cityName.isEmpty()) {
            throw new BadRequestException("cityName is missing");
        }
        return new ResponseEntity<>(weatherService.getWeather(cityName), HttpStatus.OK);
    }
}
