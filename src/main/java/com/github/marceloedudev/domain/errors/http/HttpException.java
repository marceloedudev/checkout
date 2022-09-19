package com.github.marceloedudev.domain.errors.http;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpException extends RuntimeException {

    private List<String> messages;

    public HttpException(String message) {
        super(message);
        this.messages = Arrays.asList(message);
    }

    public HttpException(List<String> messages) {
        super(messages.stream().collect(Collectors.joining(", ")));
        this.messages = messages;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        this.messages = Arrays.asList(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.messages = Arrays.asList(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
