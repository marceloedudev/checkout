package com.github.marceloedudev.infra.event.queue.consumer;

import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.queue.consumer.MessageConsumer;
import com.github.marceloedudev.pkg.queue.consumer.MessageHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class EventConsumer extends MessageConsumer {

    private final LoggerAdapter log = LoggerAdapter.getLogger(EventConsumer.class);

    @Inject
    PlaceOrderEventConsumer placeOrderEventConsumer;

    @Override
    public List<MessageHandler> getConsumes() {
        return Arrays.asList(
                placeOrderEventConsumer
        );
    }

    @Override
    public void onStart() {
        log.info("> EventConsumer -> onStart");
    }

}
