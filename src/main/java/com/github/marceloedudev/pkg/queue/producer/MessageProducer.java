package com.github.marceloedudev.pkg.queue.producer;

import com.github.marceloedudev.domain.entity.EventQueueName;
import com.github.marceloedudev.pkg.databind.ObjectMapperAdapter;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.rabbitmq.AMQPChannel;
import com.github.marceloedudev.pkg.rabbitmq.AMQPClientImpl;
import com.rabbitmq.client.AMQP;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MessageProducer {

    private final LoggerAdapter log = LoggerAdapter.getLogger(MessageProducer.class);

    private final ObjectMapperAdapter objectMapper = ObjectMapperAdapter.getFactory();

    private AMQPClientImpl amqpClient;

    private AMQPChannel channel;

    @Inject
    public MessageProducer(AMQPClientImpl amqpClient) {
        this.amqpClient = amqpClient;
    }

    public void onApplicationStart(@Observes StartupEvent event) {
        connection();
    }

    private void connection() {
        try {
            channel = amqpClient.connection().getChannel();
            log.info("> Producer: Rabbitmq connected");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(EventQueueName queue, Object value) throws Exception {
        String message = objectMapper.writeValueAsString(value);
        channel.basicPublish(queue.getQueueName(), queue.getExchangeName(), message);
    }

    public void publish(EventQueueName queue, String message) {
        channel.basicPublish(queue.getQueueName(), queue.getExchangeName(), message);
    }

    public void publish(String queueName, String exchangeName, String message, AMQP.BasicProperties basicProperties) {
        channel.basicPublish(queueName, exchangeName, message, basicProperties);
    }

}
