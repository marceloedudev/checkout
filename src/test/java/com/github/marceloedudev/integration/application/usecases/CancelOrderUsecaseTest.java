package com.github.marceloedudev.integration.application.usecases;

import com.github.marceloedudev.application.dto.CancelOrderInput;
import com.github.marceloedudev.application.dto.CancelOrderOutput;
import com.github.marceloedudev.application.usecases.CancelOrderUsecase;
import com.github.marceloedudev.domain.entity.Order;
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
public class CancelOrderUsecaseTest {

    @Inject
    CancelOrderUsecase cancelOrderUsecase;

    @InjectMock
    OrderDAODatabase orderDAODatabase;

    Long orderId = 8L;

    @BeforeEach
    public void setup() {
        Order order = OrderObjectMother.validAndWithoutItem();
        Mockito.when(orderDAODatabase.findById(orderId)).thenReturn(Optional.of(order));
    }

    @Test
    public void cancelOrder() {
        CancelOrderOutput output = cancelOrderUsecase.execute(new CancelOrderInput(orderId));
        Assertions.assertEquals(true, output.getSuccess());
    }
}
