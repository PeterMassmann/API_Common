package com.easyspec.templates;

public class FolderConfig {

    private final String name;
    private final String extension;
    private final FolderPermission read;
    private final FolderPermission write;
    private final FolderPermission delete;

    public FolderConfig(String name, String extension, FolderPermission readAccessibility, FolderPermission writeAccessibility, FolderPermission deleteAccessibility) {
        this.name = name;
        this.extension = extension;
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
