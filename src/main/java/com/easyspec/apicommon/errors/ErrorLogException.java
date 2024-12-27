package com.easyspec.apicommon.errors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter @AllArgsConstructor
public class ErrorLogException extends RuntimeException {

    private final BaseError error;

    private final String endpoint;

    private final HttpServletRequest request;

    private final Map<String, Object> customData;

    public ErrorLogException(BaseError error, String endpoint, HttpServletRequest request) {
        this.error = error;
        this.endpoint = endpoint;
        this.request = request;
        this.customData = Map.of();
    }

}
