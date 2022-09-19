package com.github.marceloedudev.domain.errors.base;

public class AppError extends RuntimeException {

    public AppError(String message) {
        super(message);
    }
    
}
