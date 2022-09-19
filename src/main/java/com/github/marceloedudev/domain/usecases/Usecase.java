package com.github.marceloedudev.domain.usecases;

public interface Usecase<Input, Output> {
    public Output execute(Input input);
}
