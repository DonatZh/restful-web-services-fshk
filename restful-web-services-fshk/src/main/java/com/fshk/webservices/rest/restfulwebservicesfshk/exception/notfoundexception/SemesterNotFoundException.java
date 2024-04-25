package com.fshk.webservices.rest.restfulwebservicesfshk.exception.notfoundexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SemesterNotFoundException extends RuntimeException{
    public SemesterNotFoundException(String message) {
        super(message);
    }
}
