package com.easyspec.templates;

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

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public FolderPermission getRead() {
        return read;
    }

    public FolderPermission getWrite() {
        return write;
    }

    public FolderPermission getDelete() {
        return delete;
    }
}
