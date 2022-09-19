package com.github.marceloedudev.unit.domain.entity.OrderItem;

import com.github.marceloedudev.domain.entity.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OrderItemTest {

    @Test
    @DisplayName("should be create order item and check total")
    public void createOrderItemAndCheckTotal() {
        OrderItem orderItem = OrderItemObjectMother.valid();
        Assertions.assertEquals(3004.0, orderItem.getTotal());
    }

}
