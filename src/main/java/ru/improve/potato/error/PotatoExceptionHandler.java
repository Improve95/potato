package ru.improve.potato.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.improve.potato.error.exceptions.AlreadyExistException;
import ru.improve.potato.error.exceptions.OnCreateException;
import ru.improve.potato.error.responseBody.CustomErrorResponse;
import ru.improve.potato.error.responseBody.DefaultErrorResponse;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class PotatoExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleAllExceptions(Exception ex) {
        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(defaultErrorResponse, determineHttpStatus(ex));
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleMyExceptions(CustomPotatoException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(ex.getMessage(), ex.getFieldsWithError(), ex.getTime());
        return new ResponseEntity<>(customErrorResponse, determineHttpStatus(ex));
    }

    private static HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof OnCreateException ||
                ex instanceof DateTimeParseException ||
                ex instanceof AlreadyExistException) {

            return HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof BadCredentialsException) {
            return HttpStatus.FORBIDDEN;
        }

        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
