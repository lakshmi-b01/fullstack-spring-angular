package net.javaspaceproject.springboot.controller;

import net.javaspaceproject.springboot.exception.ApiError;
import net.javaspaceproject.springboot.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(){
        ApiError apiError = new ApiError(400, "User not found", new Date());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

}
