package com.github.marceloedudev.domain.queue;

public interface EventQueue<Input> {
    void execute(Input input);
}
