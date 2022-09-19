package com.github.marceloedudev.pkg.rabbitmq;

public enum AMQPDeliveryModeStatus {

    NonPersistent(1),
    Persistent(2);

    private int code;

    AMQPDeliveryModeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
