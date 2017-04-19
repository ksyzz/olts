package org.ksyzz.controller;

import org.ksyzz.exception.NullEntityException;
import org.ksyzz.exception.PrivilegeException;
import org.ksyzz.exception.ConflictException;
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
    @ExceptionHandler(NullEntityException.class)
    public ResponseEntity<String> loginError(NullEntityException e){
        //返回值为responseEntity的body
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> registerError(ConflictException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PrivilegeException.class)
    public ResponseEntity<String> privilegeError(PrivilegeException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
