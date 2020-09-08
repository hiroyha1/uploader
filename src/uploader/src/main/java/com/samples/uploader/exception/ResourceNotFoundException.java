package com.samples.uploader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 498093535946022224L;

    public ResourceNotFoundException(String exception) {
        super(exception);
    }
}
