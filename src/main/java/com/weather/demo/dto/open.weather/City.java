package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

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