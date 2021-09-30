package com.mercadolibre.stock.exception;

import com.mercadolibre.stock.model.dto.ErrorDTO;
import com.mercadolibre.stock.utils.CustomLogger;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    public GlobalExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomExceptions(CustomException e, WebRequest request) {
        loggerException(e);
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), e.getError());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            httpStatus = HttpStatus.valueOf(e.getStatusCode());
        } catch (Exception ignored){}

        return handleExceptionInternal(e, errorDTO, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleProgramException(Exception e, WebRequest request) {
        loggerException(e);
        return handleExceptionInternal(e, new ErrorDTO(messageSource.getMessage("api.error.internal.unknown", null, Locale.ENGLISH)), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
        loggerException(e);
        return handleExceptionInternal(e, new ErrorDTO(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException e, WebRequest request) {
        loggerException(e);
        return handleExceptionInternal(e, new ErrorDTO(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        loggerException(e);
        return handleExceptionInternal(e, new ErrorDTO(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private void loggerException(Exception e){
        logger.error(CustomLogger.formatError(e));
    }
}