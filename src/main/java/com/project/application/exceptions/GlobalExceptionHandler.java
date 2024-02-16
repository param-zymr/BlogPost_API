package com.project.application.exceptions;

import com.project.application.dto.response.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Invalid method argument data exception handler
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<GeneralResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Invalid input data: " + errors.toString());
        return new ResponseEntity<>(new GeneralResponse(4000,
                "Invalid input Data", errors), HttpStatus.BAD_REQUEST);
    }

    // Invalid method data exception handler
    @ExceptionHandler({HandlerMethodValidationException.class})
    public ResponseEntity<GeneralResponse> handleHandlerMethodValidationException(HandlerMethodValidationException exception) {
        log.error("Invalid input data: " + exception.getMessage());
        return new ResponseEntity<>(new GeneralResponse(4000,
                "Invalid input data"), HttpStatus.BAD_REQUEST);
    }

    // Invalid method argument data type exception handler
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<GeneralResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("Invalid input data type: " + exception.getMessage());
        return new ResponseEntity<>(new GeneralResponse(4000,
                "Invalid input data type"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<GeneralResponse> handleGeneralException(Exception exception) {
        log.error("Exception Occurred: " + exception.getMessage());
        return new ResponseEntity<>(new GeneralResponse(5000,
                "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
