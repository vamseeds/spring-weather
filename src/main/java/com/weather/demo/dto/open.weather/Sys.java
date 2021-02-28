package com.weather.demo.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys{

	@JsonProperty("pod")
	private String pod;

	public String getPod(){
		return pod;
	}
}