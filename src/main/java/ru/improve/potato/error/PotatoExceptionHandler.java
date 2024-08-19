package ru.improve.potato.error;

import ru.improve.potato.core.exceptions.IncorrectRequestException;
import ru.improve.potato.core.exceptions.NoAccessException;
import ru.improve.potato.core.exceptions.transfer.NotEnoughtMoneyException;
import ru.improve.potato.core.exceptions.transfer.NotFoundException;
import ru.improve.potato.core.exceptions.user.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PotatoExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    ResponseEntity<ErrorResponse> handle(Exception exception) {
        return ResponseEntity.status(getHttpStatus(exception))
                .body(new ErrorResponse(exception.getMessage()));
    }

    private static HttpStatus getHttpStatus(Exception exception) {
        if (exception instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        }

        if (exception instanceof UserAlreadyExistException) {
            return HttpStatus.CONFLICT;
        }

        if (exception instanceof IncorrectRequestException) {
            return HttpStatus.BAD_REQUEST;
        }

        if (exception instanceof NotEnoughtMoneyException) {
            return HttpStatus.METHOD_NOT_ALLOWED;
        }

        if (exception instanceof NoAccessException) {
            return HttpStatus.FORBIDDEN;
        }

        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
