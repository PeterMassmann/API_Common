package com.easyspec.apicommon.scannable.configuration;

import com.easyspec.apicommon.errors.BaseErrorLog;
import com.easyspec.apicommon.errors.BaseErrorRepository;
import com.easyspec.apicommon.errors.ErrorLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@ControllerAdvice
public class ErrorLoggingControllerAdvisor<E extends BaseErrorLog> {

    @Value("${error.log.class}")
    private Class<E> errorLogClass;

    @Autowired
    private BaseErrorRepository<E> errorRepository;

    @ExceptionHandler(ErrorLogException.class)
    public ResponseEntity<?> handleLogError(ErrorLogException e) {
        try {
            E errorLog = errorLogClass.getDeclaredConstructor().newInstance();
            errorLog.setEndpoint(e.getEndpoint());
            errorLog.setMethod(e.getRequest().getMethod());
            errorLog.setStatus(e.getError().getStatus());
            errorLog.setErrorCode(e.getError().getCode());
            errorLog.setMessage(e.getError().getMessage());
            errorLog.setIpAddress(e.getRequest().getRemoteAddr());
            errorLog.setUserAgent(e.getRequest().getHeader("User-Agent"));
            String userId = e.getRequest().getHeader("X-User-Id");
            if (userId != null) {
                errorLog.setUserId(Long.parseLong(userId));
            }
            errorLog.setCustomData(e.getCustomData());
            return ResponseEntity.status(errorLog.getStatus()).body(errorRepository.save(errorLog));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            return ResponseEntity.status(e.getError().getStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of(
                            "endpoint", e.getEndpoint(),
                            "method", e.getRequest().getMethod(),
                            "status", e.getError().getStatus(),
                            "errorCode", e.getError().getCode(),
                            "message", e.getError().getMessage(),
                            "ipAddress", e.getRequest().getRemoteAddr(),
                            "userAgent", e.getRequest().getHeader("User-Agent"),
                            "userId", e.getRequest().getHeader("X-User-Id"),
                            "customData", e.getCustomData()
                    ));
        }
    }

}
