package com.weather.demo.proxy;

import com.weather.demo.dto.open.weather.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "open-weather", url = "${openweather.url}")
public interface OpenWeatherProxy {
    @GetMapping("/")
    public WeatherResponse getWeather(@RequestParam String q,@RequestParam String units,@RequestParam String appid);
}
