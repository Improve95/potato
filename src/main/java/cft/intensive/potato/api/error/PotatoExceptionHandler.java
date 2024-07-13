package cft.intensive.potato.api.error;

import cft.intensive.potato.core.exceptions.IncorrectRequestException;
import cft.intensive.potato.core.exceptions.transfer.NotEnoughtMoneyException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.exceptions.user.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PotatoExceptionHandler {

    /*TODO: добавить throwable... (что это такое?)*/

    @org.springframework.web.bind.annotation.ExceptionHandler
    ResponseEntity<Error> handle(Exception exception) {
        return ResponseEntity.status(getHttpStatus(exception))
                .body(new Error(exception.getMessage()));
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

        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
