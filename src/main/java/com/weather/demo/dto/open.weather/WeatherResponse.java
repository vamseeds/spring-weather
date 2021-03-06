package com.weather.demo.dto.open.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

	@JsonProperty("city")
	private City city;

	@JsonProperty("cnt")
	private int cnt;

	@JsonProperty("cod")
	private String cod;

	@JsonProperty("message")
	private double message;

	@JsonProperty("list")
	private List<ListItem> list;

	public City getCity(){
		return city;
	}

	public int getCnt(){
		return cnt;
	}

	public String getCod(){
		return cod;
	}

	public double getMessage(){
		return message;
	}

	public List<ListItem> getList(){
		return list;
	}
}