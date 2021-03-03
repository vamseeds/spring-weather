package com.weather.demo.controller;

import com.weather.demo.dto.weather.ForecastResponse;
import com.weather.demo.errors.BadRequestException;
import com.weather.demo.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@Slf4j
@Validated
@RestController
@RequestMapping("/")
public class WeatherController {

    public static final String ONLY_CHARS_REGEX = "^[a-zA-Z\\s]+$";
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public ResponseEntity<ForecastResponse> getWeatherDetails(@RequestParam(name = "cityName", required = true)
                                                              @Pattern(regexp = ONLY_CHARS_REGEX, message = "Please enter a Valid City Name") String cityName) {
        log.info("Fetching Weather Details for :{}", cityName);
        if (cityName.isEmpty()) {
            throw new BadRequestException("cityName is missing");
        }
        return new ResponseEntity<>(weatherService.getWeather(cityName.trim()), HttpStatus.OK);
    }
}
