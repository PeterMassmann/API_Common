package com.easyspec.apicommon.templates;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class EndpointConfig {

    /**
     * The path of the endpoint.
     */
    private final String path;
    private final String type;
    private final List<String> methods;
    private final Map<String, String> pathVariablePatterns;
    private final List<String> dependencies;

    public EndpointConfig(String path, String type, List<String> methods, Map<String, String> pathVariablePatterns, List<String> dependencies) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = pathVariablePatterns;
        this.dependencies = dependencies;
    }

    public EndpointConfig(String path, String type, List<String> methods, Map<String, String> pathVariablePatterns) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = pathVariablePatterns;
        this.dependencies = List.of();
    }

    public EndpointConfig(String path, String type, List<String> methods, List<String> dependencies) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = Map.of();
        this.dependencies = dependencies;
    }

    public EndpointConfig(String path, String type, String... methods) {
        this.path = path;
        this.type = type;
        this.methods = List.of(methods);
        this.pathVariablePatterns = Map.of();
        this.dependencies = List.of();
    }
}