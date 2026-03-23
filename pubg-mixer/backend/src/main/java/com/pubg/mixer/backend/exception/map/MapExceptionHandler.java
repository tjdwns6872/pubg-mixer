package com.pubg.mixer.backend.exception.map;

import com.pubg.mixer.backend.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MapExceptionHandler {

    @ExceptionHandler(MapNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMapNotFoundException(
            MapNotFoundException e, HttpServletRequest request) {

        log.warn("Not Found: {} {} | message: {}",
                request.getMethod(), request.getRequestURI(), e.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
