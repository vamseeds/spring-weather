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
public class Rain{

	@JsonProperty("3h")
	private double jsonMember3h;

	public double getJsonMember3h(){
		return jsonMember3h;
	}
}