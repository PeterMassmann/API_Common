package com.easyspec.utils;

import com.easyspec.errors.BaseError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultMatchers {

    public static ResultMatcher isError(BaseError error) {
        ObjectMapper objectMapper = new ObjectMapper();
        return result -> {
            JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
            assertThat(result.getResponse().getStatus() == error.getStatus()).isTrue();
            assertThat(
                    node.has("id")
                            && node.has("creation")
                            && node.has("endpoint")
                            && node.has("method")
                            && node.has("status")
                            && node.has("userId")
                            && node.has("ipAddress")
                            && node.has("userAgent")
                            && node.has("errorCode")
                            && node.path("errorCode").asText().equals(error.getCode())
                            && node.has("message")
                            && node.has("customData")
            ).isTrue();
        };
    }

    public static ResultMatcher isError() {
        ObjectMapper objectMapper = new ObjectMapper();
        return result -> {
            JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
            assertThat(
                    node.has("id")
                    && node.has("creation")
                    && node.has("endpoint")
                    && node.has("method")
                    && node.has("status")
                    && node.has("userId")
                    && node.has("ipAddress")
                    && node.has("userAgent")
                    && node.has("errorCode")
                    && node.has("message")
                    && node.has("customData")
            ).isTrue();
        };
    }

}
