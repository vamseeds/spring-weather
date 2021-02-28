package com.weather.demo.errors;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDetail {

    private String refId;

    private String message;

    private String detail;

}
