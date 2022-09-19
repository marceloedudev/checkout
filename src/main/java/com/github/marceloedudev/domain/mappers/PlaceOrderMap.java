package com.github.marceloedudev.domain.mappers;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.application.dto.PlaceOrderInputOrderItems;
import com.github.marceloedudev.infra.dto.PlaceOrderRequest;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderMap {

    public static PlaceOrderInput requestToInput(PlaceOrderRequest request) {
        List<PlaceOrderInputOrderItems> orderItems = new ArrayList<>();
        if (request.getOrderItems() != null && request.getOrderItems().size() != 0) {
            request.getOrderItems().stream()
                    .forEach(item -> {
                        orderItems.add(new PlaceOrderInputOrderItems(item.getIdItem(), item.getQuantity()));
                    });
        }
        PlaceOrderInput order = new PlaceOrderInput(request.getUserId(), request.getCpf(), orderItems);
        return order;
    }

}
