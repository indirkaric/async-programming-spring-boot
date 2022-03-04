package com.indir.async.app.exception.error;

public class BadRequestException extends RuntimeException {
    private final RestApiError error;

    public BadRequestException(RestApiError error) {
        this.error = error;
    }

    public RestApiError getError() {
        return error;
    }
}