package org.ksyzz.controller;

import org.ksyzz.exception.LoginException;
import org.ksyzz.exception.RegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by fengqian on 2017/4/17.
 */
@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> loginError(LoginException e){
        //返回值为responseEntity的body
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<String> registerError(RegisterException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
