package com.easyspec.templates;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.BiPredicate;

public class FolderPermission {
    private final String accessibility;
    private final BiPredicate<Long, Long> predicate;

    private FolderPermission(String accessibility, BiPredicate<Long, Long> predicate) {
        this.accessibility = accessibility;
        this.predicate = predicate;
    }

    public static FolderPermission open() { // anyone
        return new FolderPermission("public", null);
    }

    public static FolderPermission closed(BiPredicate<Long, Long> predicate) { // authenticated and matching predicate
        return new FolderPermission("private", predicate);
    }

    public static FolderPermission exclusive() { // only from this service's IP
        return new FolderPermission("exclusive", null);
    }

    public static FolderPermission disabled() {
        return new FolderPermission("disabled", null);
    }

    @JsonValue
    public String getAccessibility() {
        return accessibility;
    }

    public boolean test(Long imageId, Long userId) {
        return predicate.test(imageId, userId);
    }
}
