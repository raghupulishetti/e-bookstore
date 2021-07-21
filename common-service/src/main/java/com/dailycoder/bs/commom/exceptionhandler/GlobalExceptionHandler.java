package com.dailycoder.bs.commom.exceptionhandler;

import com.dailycoder.bs.commom.exception.BadRequestException;
import com.dailycoder.bs.commom.exception.GenericException;
import com.dailycoder.bs.commom.exception.NotFoundException;
import com.dailycoder.bs.commom.vo.Error;
import com.dailycoder.bs.commom.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BadRequestException ex) {
        ErrorResponse errorResponse = populateErrorResponse("400", ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = { NoHandlerFoundException.class,NotFoundException.class })
    public ResponseEntity<ErrorResponse> handleCustomException(NotFoundException ex) {
        ErrorResponse errorResponse = populateErrorResponse("404", ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        ErrorResponse errorResponse = populateErrorResponse("500", "Something went wrong, please try again later!");
        log.error("Something went wrong, Exception : " + ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ErrorResponse populateErrorResponse(String code, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setUuid(UUID.randomUUID());

        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);

        errorResponse.setErrors(Collections.singletonList(error));

        return errorResponse;
    }

}
