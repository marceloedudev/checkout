package com.github.marceloedudev.pkg.queue.consumer;

import com.github.marceloedudev.domain.entity.EventQueueName;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.rabbitmq.AMPQExchangeType;
import com.github.marceloedudev.pkg.rabbitmq.AMQPChannel;
import com.github.marceloedudev.pkg.rabbitmq.AMQPClientImpl;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class MessageConsumer {

    private final LoggerAdapter log = LoggerAdapter.getLogger(MessageConsumer.class);

    public abstract List<MessageHandler> getConsumes();

    public abstract void onStart();

    @Inject
    AMQPClientImpl amqpClient;

    private AMQPChannel channel;

    public void onApplicationStart(@Observes StartupEvent event) {
        connection();
    }

    private void connection() {
        try {
            channel = amqpClient.connection().getChannel();
            setupQueues(channel);
            setupConsumers();
            onStart();
            log.info("> Consumer: Rabbitmq connected");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupQueues(AMQPChannel channel) {
        Stream.of(EventQueueName.values()).forEach((queueName) -> {
            createExchangeAndQueue(channel, queueName);
        });
    }

    private void createExchangeAndQueue(AMQPChannel channel, EventQueueName eventQueueName) {
        try {
            String exchangeName = eventQueueName.getExchangeName();
            boolean durable = true;
            channel.exchangeDeclare(exchangeName, AMPQExchangeType.DIRECT, durable);
            createQueueBase(channel, eventQueueName);
            channel.queueDeclare();
            if (eventQueueName.isDlqEnabled()) {
                createQueueToDLQ(channel, eventQueueName);
            }
            if (eventQueueName.isRetryEnabled()) {
                createQueueToRetryAttempt(channel, eventQueueName);
            }
        }
        catch (Exception exception) {
            log.error("> createExchangeAndQueue create failed error={}", exception.getMessage());
        }
    }

    private void createQueueBase(AMQPChannel channel, EventQueueName eventQueueName) throws IOException {
        String queueName = eventQueueName.getQueueName();
        String exchangeName = eventQueueName.getExchangeName();
        createQueueAndBind(channel, queueName, exchangeName);
    }

    private void createQueueToDLQ(AMQPChannel channel, EventQueueName eventQueueName) throws IOException {
        String queueName = eventQueueName.getDlqName();
        String exchangeName = eventQueueName.getExchangeName();
        createQueueAndBind(channel, queueName, exchangeName);
    }

    private void createQueueToRetryAttempt(AMQPChannel channel, EventQueueName eventQueueName) throws IOException {
        String queueName = eventQueueName.getRetryName();
        String exchangeName = eventQueueName.getExchangeName();
        String routingKey = eventQueueName.getQueueName();
        Map<String, Object> queueArguments = new HashMap<>();
        queueArguments.put("x-dead-letter-exchange", exchangeName);
        queueArguments.put("x-dead-letter-routing-key", routingKey);
        createQueueAndBind(channel, queueName, exchangeName, queueArguments);
    }

    private void createQueueAndBind(AMQPChannel channel, String queueName, String exchangeName)  throws IOException {
        createQueueAndBind(channel, queueName, exchangeName, new HashMap<>());
    }

    private void createQueueAndBind(AMQPChannel channel, String queueName, String exchangeName,  Map<String, Object> queueArguments)  throws IOException {
        channel.queueDeclare(queueName, true, false, false, queueArguments);
        channel.queueBind(queueName, exchangeName, queueName);
    }

    private void setupConsumers() {
        getConsumes().stream().forEach(this::createConsumers);
    }

    private void createConsumers(MessageHandler consumer) {
        try {
            // acknowledge the message automatically upon receipt
            boolean autoAck = true;
            channel = amqpClient.connection().getChannel();
            channel.basicConsume(consumer.getQueue().getQueueName(), autoAck, consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
