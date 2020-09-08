package com.samples.uploader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -3615079470593385802L;

    public AlreadyExistsException(String exception) {
        super(exception);
    }
}
