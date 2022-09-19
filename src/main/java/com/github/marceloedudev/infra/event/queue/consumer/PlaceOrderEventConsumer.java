package com.github.marceloedudev.infra.event.queue.consumer;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.application.usecases.PlaceOrderUsecase;
import com.github.marceloedudev.domain.entity.EventQueueName;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.queue.consumer.MessageHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlaceOrderEventConsumer extends MessageHandler<PlaceOrderInput> {

    private final LoggerAdapter log = LoggerAdapter.getLogger(PlaceOrderEventConsumer.class);

    @Inject
    PlaceOrderUsecase placeOrderUsecase;

    @Override
    public Class<PlaceOrderInput> getInputClass() {
        return PlaceOrderInput.class;
    }

    @Override
    public EventQueueName getQueue() {
        return EventQueueName.PLACE_ORDER;
    }

    @Override
    public void onMessage(PlaceOrderInput input) {
        try {
            log.info("> PlaceOrderEventConsumer input={}", input);
            placeOrderUsecase.execute(input);
        }
        catch (BadRequestException e) {
            log.error("> BadRequestException -> handleDelivery exceptions={}", e.getMessages());
            // notify...
        }
        catch (NotFoundException e) {
            log.error("> NotFoundException -> handleDelivery exceptions={}", e.getMessages());
            // notify...
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
