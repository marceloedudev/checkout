package com.github.marceloedudev.pkg.rabbitmq;

import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class AMQPClientImpl implements AMQPClient {

    private final LoggerAdapter log = LoggerAdapter.getLogger(AMQPClientImpl.class);

    private RabbitMQClient rabbitMQClient;

    private AMQPChannel channel;

    public AMQPClientImpl(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
        this.channel = new AMQPChannelImpl();
    }

    @Override
    public AMQPClientImpl connection() throws IOException {
        try {
            Connection connection = rabbitMQClient.connect();
            Channel channel = connection.createChannel();
            this.channel.setChannel(channel);
        } catch (IOException e) {
            log.error("> connection to rabbitmq failed ", e.getMessage());
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public AMQPChannel getChannel() {
        return channel;
    }

}
