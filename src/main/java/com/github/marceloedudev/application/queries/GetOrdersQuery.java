package com.github.marceloedudev.application.queries;

import com.github.marceloedudev.application.dto.GetOrdersInput;
import com.github.marceloedudev.application.dto.GetOrdersOutput;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.entity.Pagination;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.domain.queries.GetOrders;
import com.github.marceloedudev.domain.queries.Queries;
import com.github.marceloedudev.infra.database.dao.OrderDAODatabase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetOrdersQuery implements Queries<GetOrdersInput, GetOrdersOutput>  {

    private OrderDAODatabase orderDAODatabase;

    @Inject
    public GetOrdersQuery(OrderDAODatabase orderDAODatabase) {
        this.orderDAODatabase = orderDAODatabase;
    }

    @Override
    public GetOrdersOutput execute(GetOrdersInput input) {
        GetOrders getOrders = new GetOrders(input.getUserId(), input.getPage(), input.getSize());
        if (getOrders.validate()) {
            throw new BadRequestException(getOrders.getNotifications());
        }
        List<Order> orders = orderDAODatabase.findAll(input.getUserId(), new Pagination(input.getPage(), input.getSize()));
        return new GetOrdersOutput(orders);
    }

}
