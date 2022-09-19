package com.github.marceloedudev.domain.errors.http;

import java.util.List;

public class BadRequestException extends HttpException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<String> messages) {
        super(messages);
    }

}
