package com.weather.demo.dto.open.weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListItem {

    @JsonProperty("dt")
    private Date dt;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("dt_txt")
    private String dtTxt;

    @JsonProperty("snow")
    private Snow snow;

    @JsonProperty("weather")
    private List<WeatherItem> weather;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("wind")
    private Wind wind;

    public Date getDt() {
        return dt;
    }

    public Rain getRain() {
        return rain;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public Snow getSnow() {
        return snow;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public Wind getWind() {
        return wind;
    }

    public LocalDateTime getLocalDateTime() {
        if (Objects.nonNull(this.getDtTxt())) {
            try {
                return LocalDateTime.parse(this.getDtTxt()
                        , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            }catch (Exception e){
                log.error("Got exception while converting Date : {} ",e.getMessage());
                return null;
            }
			}
        else
        	return null;
    }
}