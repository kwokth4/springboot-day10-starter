package com.rest.springbootemployee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCompanyFoundException extends RuntimeException {
    public NoCompanyFoundException() {
        super("No company found");
    }
}
