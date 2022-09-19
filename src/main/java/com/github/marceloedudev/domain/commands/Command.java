package com.github.marceloedudev.domain.commands;

public interface Command<Input> {
    public void execute(Input input);
}
