package com.easyspec.apicommon.templates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter @AllArgsConstructor
public class FolderConfig {

    private String path;

    private long maxSize;

    private String writeCheckPath;

    private String readCheckPath;

    private boolean allowDelete;

    private Set<String> allowedExtensions;

    private Set<String> requiredMetadata;

}
