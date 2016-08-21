package com.xieziming.stap.channel.config;

import com.xieziming.stap.core.exceptions.StapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Suny on 7/21/16.
 */
@ControllerAdvice
public class StapExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(StapExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception exception) {
        logger.error("Service exception: ",exception);
        if(exception instanceof EmptyResultDataAccessException){
            return new ResponseEntity<Object>(new StapException("Record not found"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(new StapException(exception), HttpStatus.BAD_REQUEST);
    }
}
