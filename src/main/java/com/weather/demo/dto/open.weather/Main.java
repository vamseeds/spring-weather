package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Main{

	@JsonProperty("temp")
	private BigDecimal temp;

	@JsonProperty("temp_min")
	private BigDecimal tempMin;

	@JsonProperty("grnd_level")
	private BigDecimal grndLevel;

	@JsonProperty("temp_kf")
	private BigDecimal tempKf;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("pressure")
	private BigDecimal pressure;

	@JsonProperty("sea_level")
	private BigDecimal seaLevel;

	@JsonProperty("temp_max")
	private BigDecimal tempMax;

	public BigDecimal getTemp(){
		return temp;
	}

	public BigDecimal getTempMin(){
		return tempMin;
	}

	public BigDecimal getGrndLevel(){
		return grndLevel;
	}

	public BigDecimal getTempKf(){
		return tempKf;
	}

	public int getHumidity(){
		return humidity;
	}

	public BigDecimal getPressure(){
		return pressure;
	}

	public BigDecimal getSeaLevel(){
		return seaLevel;
	}

	public BigDecimal getTempMax(){
		return tempMax;
	}
}