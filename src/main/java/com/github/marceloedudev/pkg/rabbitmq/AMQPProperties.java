package com.github.marceloedudev.pkg.rabbitmq;

import com.rabbitmq.client.AMQP;

public interface AMQPProperties {
    AMQP.BasicProperties updateExpirationBasicProperty(AMQP.BasicProperties properties, long expiration);
}
