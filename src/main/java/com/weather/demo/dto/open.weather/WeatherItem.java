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
public class WeatherItem{

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("description")
	private String description;

	@JsonProperty("main")
	private String main;

	@JsonProperty("id")
	private int id;

	public String getIcon(){
		return icon;
	}

	public String getDescription(){
		return description;
	}

	public String getMain(){
		return main;
	}

	public int getId(){
		return id;
	}
}