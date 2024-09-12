package com.easyspec.templates;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestUserHeader {

    public static RequestUserHeader of(HttpServletRequest request) {
        String userId = request.getHeader("X-User-Id");
        List<String> adminPermissions = List.of(request.getHeader("X-Admin-Permissions").split(","));
        return new RequestUserHeader(userId, adminPermissions);
    }

    private final String userId;

    private final List<String> adminPermissions;

    private RequestUserHeader(String userId, List<String> adminPermissions) {
        this.userId = userId;
        this.adminPermissions = adminPermissions;
    }

    private boolean hasPermission(String permission) {
        return adminPermissions.contains(permission);
    }

}
