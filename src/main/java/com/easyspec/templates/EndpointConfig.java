package com.easyspec.templates;

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
    private final List<String> requestedPermissions; // The permissions that should be passed along to the microservice

    public EndpointConfig(String path, String type, List<String> methods, Map<String, String> pathVariablePatterns, List<String> dependencies, List<String> requestedPermissions) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = pathVariablePatterns;
        this.dependencies = dependencies;
        this.requestedPermissions = requestedPermissions;
    }

    public EndpointConfig(String path, String type, List<String> methods, Map<String, String> pathVariablePatterns, List<String> requestedPermissions) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = pathVariablePatterns;
        this.dependencies = List.of();
        this.requestedPermissions = requestedPermissions;
    }

    public EndpointConfig(String path, String type, List<String> methods) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = Map.of();
        this.dependencies = List.of();
        this.requestedPermissions = List.of();
    }

    public EndpointConfig(String path, String type, List<String> methods, List<String> dependencies) {
        this.path = path;
        this.type = type;
        this.methods = methods;
        this.pathVariablePatterns = Map.of();
        this.dependencies = dependencies;
        this.requestedPermissions = List.of();
    }
}