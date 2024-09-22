package com.easyspec.services;

import com.easyspec.errors.BaseError;
import com.easyspec.errors.BaseErrorLog;
import com.easyspec.errors.BaseErrorRepository;
import com.easyspec.errors.ErrorLogInstantiationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ErrorLoggerService<E extends BaseErrorLog> {

    @Autowired // this should be then changed on each microservice
    private BaseErrorRepository<E> baseErrorRepository;

    private final Class<E> errorLogClass;

    public ErrorLoggerService(Class<E> errorLogClass) {
        this.errorLogClass = errorLogClass;
    }

    public E logError(
            String endpoint,
            BaseError error,
            HttpServletRequest request,
            Map<String, Object> data
    ) throws ErrorLogInstantiationException {
        try {
            E errorLog = errorLogClass.getDeclaredConstructor().newInstance();
            errorLog.setEndpoint(endpoint);
            errorLog.setMethod(request.getMethod());
            errorLog.setStatus(error.getStatus());
            errorLog.setErrorCode(error.getCode());
            errorLog.setMessage(error.getMessage());
            errorLog.setIpAddress(request.getRemoteAddr());
            errorLog.setUserAgent(request.getHeader("User-Agent"));
            String userId = request.getHeader("X-User-Id");
            if (userId != null) {
                errorLog.setUserId(Long.parseLong(userId));
            }
            errorLog.setCustomData(data);
            return baseErrorRepository.save(errorLog);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new ErrorLogInstantiationException(e);
        }
    }

    public E logError(BaseError error, String endpoint, HttpServletRequest request, Map<String, Object> data) throws ErrorLogInstantiationException {
        return this.logError(endpoint, error, request, data);
    }

    public E logError(BaseError error, String endpoint, HttpServletRequest request) throws ErrorLogInstantiationException {
        return this.logError(error, endpoint, request, Map.of());
    }

    @Scheduled(fixedRate = 60000)
    public void deleteOldErrors() {
        this.baseErrorRepository.deleteAllByCreationBefore(LocalDateTime.now().minusDays(7));
    }

}
