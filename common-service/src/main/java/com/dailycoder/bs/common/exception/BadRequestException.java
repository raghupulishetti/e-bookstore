package com.dailycoder.bs.common.exception;

public class BadRequestException extends RuntimeException{
    BadRequestException(String message){
        super(message);
    }
}
