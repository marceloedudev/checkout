package com.github.marceloedudev.pkg.rabbitmq;

import java.io.IOException;

public interface AMQPClient {
    AMQPClientImpl connection() throws IOException;
    AMQPChannel getChannel();
}
