package com.github.marceloedudev.integration.application.queries;

import com.github.marceloedudev.application.dto.GetOrdersInput;
import com.github.marceloedudev.application.dto.GetOrdersOutput;
import com.github.marceloedudev.application.queries.GetOrdersQuery;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.entity.Pagination;
import com.github.marceloedudev.infra.database.dao.OrderDAODatabase;
import com.github.marceloedudev.unit.domain.entity.Order.OrderObjectMother;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class GetOrdersQueryTest {

    @Inject
    GetOrdersQuery ordersQuery;

    @InjectMock
    OrderDAODatabase orderDAODatabase;

    private Long userId = 2L;

    private int page = 1;

    private int size = 10;

    @BeforeEach
    public void setup() {
        Order order = OrderObjectMother.validAndWithItem();
        order.setIdOrder(1L);
        List<Order> orders = Arrays.asList(order);
        Mockito.when(orderDAODatabase.findAll(Mockito.anyLong(), Mockito.any(Pagination.class))).thenReturn(orders);
    }

    @Test
    public void ordersByUser() {
        GetOrdersOutput orders = ordersQuery.execute(new GetOrdersInput(userId, page, size));
        Assertions.assertEquals(1, orders.getOrders().size());
    }
}
