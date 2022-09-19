package com.github.marceloedudev.infra.database.repository;

import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.repository.OrderRepository;
import com.github.marceloedudev.infra.database.base.OrderPanache;
import com.github.marceloedudev.models.panache.OrderModel;
import com.github.marceloedudev.infra.database.base.OrderItemPanache;
import com.github.marceloedudev.models.panache.OrderItemModel;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderRepositoryDatabase implements OrderRepository {

    private OrderPanache orderPanache;
    private OrderItemPanache orderItemPanache;

    @Inject
    public OrderRepositoryDatabase(OrderPanache orderPanache, OrderItemPanache orderItemPanache) {
        this.orderPanache = orderPanache;
        this.orderItemPanache = orderItemPanache;
    }

    @Override
    public void create(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setCpf(order.getCpf());
        orderModel.setUser(order.getUserId());
        orderModel.setStatus(order.getStatus());
        orderPanache.persist(orderModel);
        List<OrderItemModel> orderItemModelList = new ArrayList<>();
        order.getOrderItems().forEach(item -> {
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setPrice(item.getPrice());
            orderItemModel.setQuantity(item.getQuantity());
            orderItemModel.setOrderId(orderModel.getId());
            orderItemModel.setItemId(item.getIdItem());
            orderItemModelList.add(orderItemModel);
        });
        orderItemPanache.persist(orderItemModelList);
    }

    @Override
    public void updateStatus(int status) {
        orderPanache.update("status = :status", Parameters.with("status", status));
    }
}
