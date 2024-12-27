package com.easyspec.apicommon.errors;

public interface BaseError {

    String getCode();

    int getStatus();

    String getMessage();

}
