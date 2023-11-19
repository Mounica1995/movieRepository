package com.software.movieservice.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Getter
    static class Error {
        private String message;
        private String reason;

        public Error(String message, String reason) {
            this.message = message;
            this.reason = reason;
        }
    }

    //400 bad Request
    @ExceptionHandler({InvalidDataException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleInvalidException(Throwable t) {
        log.warn(t.getMessage());
        return new Error(HttpStatus.BAD_REQUEST.getReasonPhrase(), t.getMessage());
    }

    //404 Exception
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(NotFoundException ex) {
        log.warn(ex.getMessage());
        return new Error(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    }

    //500 internal server error
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleNotFoundException(Exception ex) {
        log.error(ex.getMessage());
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
    }

}