package com.dailycoder.bs.commom.exception;

public class BadRequestException extends RuntimeException{
    BadRequestException(String message){
        super(message);
    }
}
