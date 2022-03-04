package com.indir.async.app.exception.handler;

import com.indir.async.app.exception.error.BadRequestException;
import com.indir.async.app.exception.error.RestApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestApiError> handle(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        RestApiError error = new RestApiError("field_error", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestApiError> handle(BadRequestException ex) {
        return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiError> handle(Exception ex) {
        RestApiError error = new RestApiError("internal_server_error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
