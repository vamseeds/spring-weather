package com.weather.demo.errors;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("methodKey : {} , errorResponse : {}", methodKey, response);
        switch (response.status()) {
            case 400:
                throw new BadRequestException("Invalid Input");
            case 404:
                throw new NotFoundException("Resource Not found");
            case 500:
                throw new InternalServerError("Generic Internal Server Error");
            default:
                break;
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
