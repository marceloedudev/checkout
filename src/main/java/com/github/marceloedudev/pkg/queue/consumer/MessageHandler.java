package com.github.marceloedudev.pkg.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloedudev.domain.entity.EventQueueName;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public abstract class MessageHandler<T> implements Consumer {

    private final LoggerAdapter log = LoggerAdapter.getLogger(MessageHandler.class);

    @Inject
    MessageErrorHandler messageErrorHandler;

    public abstract Class<T> getInputClass();

    public abstract EventQueueName getQueue();

    public abstract void onMessage(T payload);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleConsumeOk(String s) {

    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        messageErrorHandler.setQueueName(getQueue());
        try {
            T body = objectMapper.readValue(bytes, getInputClass());
            log.info("> handleDelivery -> prepare -> body={}", body);
            onMessage(body);
            log.info("> handleDelivery -> success -> body={}", body);
        }
        catch (Exception e) {
            log.error("> handleDelivery -> failed exceptions={}", e.getMessage());
            messageErrorHandler.retryAttempt(envelope, basicProperties, bytes);
        }
    }
}
