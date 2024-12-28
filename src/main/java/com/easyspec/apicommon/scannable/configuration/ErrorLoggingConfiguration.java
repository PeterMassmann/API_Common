package com.easyspec.apicommon.scannable.configuration;

import com.easyspec.apicommon.errors.BaseErrorLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorLoggingConfiguration {

    @Value("${error.log.class}")
    private Class<? extends BaseErrorLog> errorLogClass;

    @Bean
    public ErrorLoggingControllerAdvisor<?> errorLoggingControllerAdvisor(ApplicationContext context) {
        return new ErrorLoggingControllerAdvisor<>(errorLogClass, context);
    }

}
