package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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