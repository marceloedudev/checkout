package com.github.marceloedudev.pkg.rabbitmq;

import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.queue.consumer.MessageHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AMQPChannelImpl implements AMQPChannel {

    private final LoggerAdapter log = LoggerAdapter.getLogger(AMQPChannelImpl.class);

    private Channel channel;

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void basicPublish(String queueName, String exchangeName, String message) {
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties(
                "application/json",
                "UTF-8",
                new HashMap<>(),
                AMQPDeliveryModeStatus.NonPersistent.getCode(),
                0,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        basicPublish(queueName, exchangeName, message, basicProperties);
    }

    @Override
    public void basicPublish(final String queueName, final String exchangeName, final String message, final AMQP.BasicProperties basicProperties) {
        try {
            log.info("> basicPublish queue={} message={}", queueName, message);
            byte[] buffer = message.getBytes(StandardCharsets.UTF_8);
            channel.basicPublish(exchangeName, queueName, basicProperties, buffer);
        } catch (IOException e) {
            log.error("> basicPublish failed error={}", e.getMessage());
        }
    }

    @Override
    public void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException {
        channel.basicNack(deliveryTag, multiple, requeue);
    }

    @Override
    public void basicReject(long deliveryTag, boolean requeue) throws IOException {
        channel.basicReject(deliveryTag, requeue);
    }

    @Override
    public void basicAck(long deliveryTag, boolean requeue) throws IOException {
        channel.basicAck(deliveryTag, requeue);
    }

    @Override
    public void exchangeDeclare(String exchangeName, AMPQExchangeType typeExchange, boolean durable) throws IOException {
        try {
            channel.exchangeDeclare(exchangeName, typeExchange.getType(), durable);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void queueDeclare() throws IOException {
        channel.queueDeclare();
    }

    @Override
    public void basicConsume(String queueName, boolean autoAck, MessageHandler consumer) throws IOException {
        channel.basicConsume(queueName, autoAck, consumer);
    }

    @Override
    public void queueDeclare(String queueName, boolean durable, boolean autoDelete, boolean closeChannelOnUnsubscribe) throws IOException {
        channel.queueDeclare(queueName, durable, autoDelete, closeChannelOnUnsubscribe, new HashMap<>());
    }

    @Override
    public void queueDeclare(String queueName, boolean durable, boolean autoDelete, boolean closeChannelOnUnsubscribe, Map<String, Object> queueArguments) throws IOException {
        channel.queueDeclare(queueName, durable, autoDelete, closeChannelOnUnsubscribe, queueArguments);
    }

    @Override
    public void queueBind(String queue, String exchange, String routingKey) throws IOException {
        channel.queueBind(queue, exchange, routingKey);
    }
}
