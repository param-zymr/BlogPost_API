package com.project.application.exceptions;

import com.project.application.dto.response.GeneralResponse;
import com.project.application.dto.response.GeneralResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Invalid argument data exception handler
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Invalid input data: " + errors.toString());
        return new ResponseEntity<>(new GeneralResponseData(4000,
                "Invalid input Data", errors), HttpStatus.BAD_REQUEST);
    }

    // Invalid argument data type exception handler
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("Invalid input data type: " + exception.getMessage());
        return new ResponseEntity<>(new GeneralResponse(4000,
                "Invalid input data type"), HttpStatus.BAD_REQUEST);
    }
}
