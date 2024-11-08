package com.easyspec.utils;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Base64;

public class BasicAuthRequestPostProcessor implements RequestPostProcessor {

    private final String headerValue;

    public BasicAuthRequestPostProcessor(String username, String password) {
        this.headerValue = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    @Override
    public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        request.addHeader("Authorization", headerValue);
        return request;
    }
}