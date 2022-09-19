package com.github.marceloedudev.pkg.queue.consumer;

import com.github.marceloedudev.domain.entity.EventQueueName;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.queue.producer.MessageProducer;
import com.github.marceloedudev.pkg.rabbitmq.AMQPProperties;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MessageErrorHandler {

    private final LoggerAdapter log = LoggerAdapter.getLogger(MessageErrorHandler.class);
    private final String PropertyKeyXDeath = "x-death";

    private MessageProducer messageProducer;

    private EventQueueName eventQueueName;

    private AMQPProperties amqpProperties;

    @Inject
    public MessageErrorHandler(MessageProducer messageProducer, AMQPProperties amqpProperties) {
        this.messageProducer = messageProducer;
        this.amqpProperties = amqpProperties;
    }

    public void setQueueName(EventQueueName eventQueueName) {
        this.eventQueueName = eventQueueName;
    }

    private void publishToDLQ(Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) {
        try {
            messageProducer.publish(eventQueueName.getDlqName(), eventQueueName.getExchangeName(), new String(bytes, StandardCharsets.UTF_8), basicProperties);
        } catch (Exception exception) {
            log.error("> error publishToRetry");
        }
    }

    private void publishToRetry(Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) {
        try {
            messageProducer.publish(eventQueueName.getRetryName(), eventQueueName.getExchangeName(), new String(bytes, StandardCharsets.UTF_8), basicProperties);
        } catch (Exception exception) {
            log.error("> error publishToRetry");
        }
    }

    public void retryAttempt(Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) {
        long count = getRetryAttemptCount(basicProperties);
        log.info("> retryAttempt header={}", basicProperties.getHeaders());
        if (count <= eventQueueName.getMaxRetryAttempt()) {
            long retryInterval = eventQueueName.getRetryInterval(count);
            publishToRetry(envelope, amqpProperties.updateExpirationBasicProperty(basicProperties, retryInterval), bytes);
            return;
        }
        basicProperties.getHeaders().remove(PropertyKeyXDeath);
        publishToDLQ(envelope, basicProperties, bytes);
    }

    private long getRetryAttemptCount(AMQP.BasicProperties basicProperties) {
        long count = 0L;
        Map<String, Object> headers = basicProperties.getHeaders();
        if (headers.containsKey(PropertyKeyXDeath)) {
            List xDeathProp = (List) Arrays.asList(headers.get(PropertyKeyXDeath)).get(0);
            int countProp = Integer.parseInt(((((Map) xDeathProp.get(0)).get("count").toString())));
            count = countProp;
        }
        count++;
        return count;
    }
}
