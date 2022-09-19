package com.github.marceloedudev.application.commands;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.domain.commands.Command;
import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.infra.event.queue.producer.PlaceOrderEventProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlaceOrderCommand implements Command<PlaceOrderInput> {

    private PlaceOrderEventProducer placeOrderEventProducer;

    @Inject
    public PlaceOrderCommand(PlaceOrderEventProducer placeOrderEventProducer) {
        this.placeOrderEventProducer = placeOrderEventProducer;
    }

    @Override
    public void execute(PlaceOrderInput input) {
        Order order = new Order(input.getUserId(), input.getCpf());
        if (order.validate(input.getOrderItems().size())) {
            throw new BadRequestException(order.getNotifications());
        }
        input.getOrderItems().forEach(itemOrder -> {
            Item item = new Item();
            item.setIdItem(itemOrder.getIdItem());
            order.addItem(item, itemOrder.getQuantity());
        });
        placeOrderEventProducer.execute(input);
    }
}
