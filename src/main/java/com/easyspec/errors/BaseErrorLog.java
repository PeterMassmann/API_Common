package com.easyspec.errors;

import com.easyspec.converters.Map2JsonConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseErrorLog {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creation;

    private String endpoint;

    private String method;

    private int status;

    @Nullable
    private Long userId;

    private String ipAddress;

    private String userAgent;

    private String errorCode;

    private String message;

    @Convert(converter = Map2JsonConverter.class)
    private Map<String, Object> customData;

}
