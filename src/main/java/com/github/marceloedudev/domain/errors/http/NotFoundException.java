package com.github.marceloedudev.domain.errors.http;

import java.util.List;

public class NotFoundException extends HttpException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(List<String> messages) {
        super(messages);
    }
}
