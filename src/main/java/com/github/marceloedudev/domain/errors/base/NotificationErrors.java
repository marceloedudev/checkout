package com.github.marceloedudev.domain.errors.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://martinfowler.com/articles/replaceThrowWithNotification.html
public class NotificationErrors {

    private List<AppError> errors = new ArrayList<>();

    public void addError(String message) {
        errors.add(new AppError(message));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrorsMessage() {
        return errors.stream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());
    }

    public List<AppError> getErrors() {
        return errors;
    }
}
