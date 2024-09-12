package com.easyspec.templates;

import lombok.Getter;

@Getter
public class FolderConfig {

    private final String name;
    private final String extension;
    private final long maxFileSize; // in bytes
    private final FolderPermission read;
    private final FolderPermission write;
    private final FolderPermission delete;

    public FolderConfig(String name, String extension, long maxFileSize, FolderPermission readAccessibility, FolderPermission writeAccessibility, FolderPermission deleteAccessibility) {
        this.name = name;
        this.extension = extension;
        this.maxFileSize = maxFileSize;
        this.read = readAccessibility;
        this.write = writeAccessibility;
        this.delete = deleteAccessibility;
    }
}
