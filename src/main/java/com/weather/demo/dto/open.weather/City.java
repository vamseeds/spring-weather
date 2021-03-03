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
public class City{

	@JsonProperty("country")
	private String country;

	@JsonProperty("coord")
	private Coord coord;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public String getCountry(){
		return country;
	}

	public Coord getCoord(){
		return coord;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}