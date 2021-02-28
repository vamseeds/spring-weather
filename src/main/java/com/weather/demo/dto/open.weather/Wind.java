package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private double deg;

	@JsonProperty("speed")
	private double speed;

	public double getDeg(){
		return deg;
	}

	public double getSpeed(){
		return speed;
	}
}