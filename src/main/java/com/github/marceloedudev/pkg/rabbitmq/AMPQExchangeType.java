package com.github.marceloedudev.pkg.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;

public enum AMPQExchangeType {

    DIRECT(BuiltinExchangeType.DIRECT),
    FANOUT(BuiltinExchangeType.FANOUT),
    TOPIC(BuiltinExchangeType.TOPIC),
    HEADERS(BuiltinExchangeType.HEADERS);

    private final BuiltinExchangeType type;

    AMPQExchangeType(BuiltinExchangeType type) {
        this.type = type;
    }

    public BuiltinExchangeType getType() {
        return this.type;
    }
}
