package com.github.marceloedudev.application.queries;

import com.github.marceloedudev.application.dto.GetOrderOutput;
import com.github.marceloedudev.application.dto.GetOrderInput;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.domain.queries.GetOrder;
import com.github.marceloedudev.domain.queries.Queries;
import com.github.marceloedudev.infra.database.dao.OrderDAODatabase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class GetOrderQuery implements Queries<GetOrderInput, GetOrderOutput> {

    private OrderDAODatabase orderDAODatabase;

    @Inject
    public GetOrderQuery(OrderDAODatabase orderDAODatabase) {
        this.orderDAODatabase = orderDAODatabase;
    }

    @Override
    public GetOrderOutput execute(GetOrderInput input) {
        GetOrder getOrder = new GetOrder(input.getOrderId(), input.getUserId());
        if (getOrder.validate()) {
            throw new BadRequestException(getOrder.getNotifications());
        }
        Optional<Order> orderDB = orderDAODatabase.findByIdAndUser(input.getOrderId(), input.getUserId());
        if (orderDB.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        Order order = orderDB.get();
        return new GetOrderOutput(order);
    }

}
