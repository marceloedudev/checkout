package com.github.marceloedudev.domain.queries;

public interface Queries<Input, Output> {
    public Output execute(Input input);
}
