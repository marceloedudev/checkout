package com.github.marceloedudev.application.usecases;

import com.github.marceloedudev.application.dto.CancelOrderInput;
import com.github.marceloedudev.application.dto.CancelOrderOutput;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.usecases.Usecase;
import com.github.marceloedudev.infra.database.dao.OrderDAODatabase;
import com.github.marceloedudev.infra.database.repository.OrderRepositoryDatabase;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.infra.metrics.CancelOrderMetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class CancelOrderUsecase implements Usecase<CancelOrderInput, CancelOrderOutput> {

    private OrderDAODatabase orderDAODatabase;
    private OrderRepositoryDatabase orderRepositoryDatabase;
    private CancelOrderMetrics cancelOrderMetrics;

    public CancelOrderUsecase(OrderDAODatabase orderDAODatabase, OrderRepositoryDatabase orderRepositoryDatabase, CancelOrderMetrics cancelOrderMetrics) {
        this.orderDAODatabase = orderDAODatabase;
        this.orderRepositoryDatabase = orderRepositoryDatabase;
        this.cancelOrderMetrics = cancelOrderMetrics;
    }

    @Override
    @Transactional
    public CancelOrderOutput execute(CancelOrderInput input) {
        Optional<Order> orderDB = orderDAODatabase.findById(input.getOrderId());
        if (orderDB.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        Order order = orderDB.get();
        order.cancel();
        orderRepositoryDatabase.updateStatus(order.getStatus());
        cancelOrderMetrics.addCountSuccess();
        return new CancelOrderOutput("Cancel order successfully");
    }

}
