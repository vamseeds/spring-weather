package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain{

	@JsonProperty("3h")
	private double jsonMember3h;

	public double getJsonMember3h(){
		return jsonMember3h;
	}
}