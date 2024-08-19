package ru.improve.potato.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.improve.potato.core.exceptions.user.PersonOnCreateException;

@RestControllerAdvice
@Slf4j
public class PotatoExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    ResponseEntity<ErrorResponse> handle(DefaultPotatoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getFieldsWithError(), ex.getTime());
        return new ResponseEntity<>(errorResponse, determineHttpStatus(ex));
    }

    private static HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof PersonOnCreateException) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
