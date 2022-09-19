package com.github.marceloedudev.pkg.rabbitmq;

import com.github.marceloedudev.pkg.queue.consumer.MessageHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

public interface AMQPChannel {
    void setChannel(Channel channel);
    void basicPublish(String queueName, String exchangeName, String message);
    void basicPublish(String queueName, String exchangeName, String message, AMQP.BasicProperties basicProperties);
    void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException;
    void basicReject(long deliveryTag, boolean requeue) throws IOException;
    void basicAck(long deliveryTag, boolean requeue) throws IOException;
    void exchangeDeclare(String exchangeName,AMPQExchangeType typeExchange, boolean durable) throws IOException;
    void queueDeclare() throws IOException;
    void basicConsume(String queueName, boolean autoAck, MessageHandler consumer) throws IOException;
    void queueDeclare(String queueName, boolean durable, boolean autoDelete, boolean closeChannelOnUnsubscribe) throws IOException;
    void queueDeclare(String queueName, boolean durable, boolean autoDelete, boolean closeChannelOnUnsubscribe, Map<String, Object>  queueArguments) throws IOException;
    void queueBind(String queue, String exchange, String routingKey) throws IOException;
}
