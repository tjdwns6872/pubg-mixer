package com.pubg.mixer.backend.exception.member;

import com.pubg.mixer.backend.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MemberExceptionHandler {

    /**
     * 리소스가 존재하지 않는 경우를 404로 응답한다.
     */
    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMemberNotFoundException(
            MemberNotFoundException e, HttpServletRequest request) {

        log.warn("Not Found: {} {} | message: {}",
                request.getMethod(), request.getRequestURI(), e.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * 비즈니스 입력 오류(요청 자체가 잘못된 경우)를 400으로 응답한다.
     *
     * <p>Bean Validation으로 잡히지 않는 케이스(예: 공백 문자열, 수정할 값 없음)를 포함한다.</p>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) {

        log.warn("Bad Request: {} {} | message: {}",
                request.getMethod(), request.getRequestURI(), e.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
