package com.ctrlcutter.api.ctrl_webapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.AuthenticationException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.ExistingParameterException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.MissingParameterException;
import com.ctrlcutter.api.ctrl_webapi.exception.exceptions.NoCustomerException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ExistingParameterException.class)
    public ResponseEntity<ExceptionResponse> handleExistingParameterException(ExistingParameterException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), e.getParameters()), HttpStatus.IM_USED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<ExceptionResponse> handleMissingParameterException(MissingParameterException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoCustomerException.class)
    public ResponseEntity<ExceptionResponse> handleNoCustomerException(NoCustomerException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
}
