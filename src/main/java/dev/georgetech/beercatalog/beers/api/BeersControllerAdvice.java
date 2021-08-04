package dev.georgetech.beercatalog.beers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class BeersControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleILAE(IllegalArgumentException exception) {
        String exceptionMessage = exception.getMessage();
        log.error("IllegalArgumentException: {}", exceptionMessage);
        return exceptionMessage;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleILSE(IllegalStateException exception) {
        String exceptionMessage = exception.getMessage();
        log.error("IllegalStateException exception: {}", exceptionMessage);
        return exceptionMessage;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleILSE(RuntimeException exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Unexpected exception: {}", exceptionMessage);
        return exceptionMessage;
    }

}
