package com.github.marceloedudev.pkg.rabbitmq;

import com.rabbitmq.client.AMQP;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AMQPPropertiesImpl implements AMQPProperties{
    @Override
    public AMQP.BasicProperties updateExpirationBasicProperty(AMQP.BasicProperties properties, long expiration) {
        return new AMQP.BasicProperties(
                "application/json",
                "UTF-8",
                properties.getHeaders(),
                properties.getDeliveryMode(),
                properties.getPriority(),
                properties.getCorrelationId(),
                properties.getReplyTo(),
                String.valueOf(expiration),
                properties.getMessageId(),
                properties.getTimestamp(),
                properties.getType(),
                properties.getUserId(),
                properties.getAppId(),
                properties.getClusterId()
        );
    }
}
