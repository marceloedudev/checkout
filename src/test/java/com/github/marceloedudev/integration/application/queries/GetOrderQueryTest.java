package com.github.marceloedudev.integration.application.queries;

import com.github.marceloedudev.application.dto.GetOrderInput;
import com.github.marceloedudev.application.dto.GetOrderOutput;
import com.github.marceloedudev.application.queries.GetOrderQuery;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.infra.database.dao.OrderDAODatabase;
import com.github.marceloedudev.unit.domain.entity.Order.OrderObjectMother;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Optional;

@QuarkusTest
public class GetOrderQueryTest {

    @Inject
    GetOrderQuery orderQuery;

    @InjectMock
    OrderDAODatabase orderDAODatabase;

    private Long orderId = 5L;
    private Long userId = 2L;

    @BeforeEach
    public void setup() {
        Order order = OrderObjectMother.validAndWithItem();
        order.setIdOrder(orderId);
        Mockito.doReturn(Optional.of(order)).when(orderDAODatabase).findByIdAndUser(orderId, userId);
    }

    @Test
    public void orderByUser() {
        GetOrderOutput order = orderQuery.execute(new GetOrderInput(orderId, userId));
        Assertions.assertEquals(5L, order.getOrder().getIdOrder());
    }

    @Test
    public void invalidOrderByUser() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            orderQuery.execute(new GetOrderInput(6L, 3L));
        });
    }
}
