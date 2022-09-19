package com.github.marceloedudev.unit.domain.entity.Order;

import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.entity.OrderStatus;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OrderTest {

    @Test
    @DisplayName("should be create order without item")
    public void createOrderWithoutItems() {
        Order order = OrderObjectMother.validAndWithoutItem();
        Assertions.assertEquals(0, order.getOrderItems().size());
    }

    @Test
    @DisplayName("should be create order with items")
    public void createOrderWith4Items() {
        Order order = OrderObjectMother.withItemsValid();
        Assertions.assertEquals(750.6, order.getTotal());
    }

    @Test
    @DisplayName("throws an error when called with item id duplicated")
    public void throwsAnErrorWhenCalledWithItemIdDuplicated() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            OrderObjectMother.withItemsDuplicated();
        });
    }

    @Test
    @DisplayName("throws an error when called with invalid quantity")
    public void throwsAnErrorWhenCalledWithInvalidQuantity() {
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            OrderObjectMother.withItemsInvalidQuantity();
        });
        Assertions.assertEquals(1, exception.getMessages().size());
    }

    @Test
    @DisplayName("should be create order with status waiting")
    public void createOrderWithStatusWaiting() {
        Order order = OrderObjectMother.validAndWithoutItem();
        Assertions.assertEquals(1, order.getStatus());
    }

    @Test
    @DisplayName("should be create order and changes status to canceled")
    public void createOrderAndChangesStatusToCanceled() {
        Order order = OrderObjectMother.validAndWithoutItem();
        order.cancel();
        Assertions.assertEquals(2, order.getStatus());
    }

    @Test
    @DisplayName("should be create order and changes status")
    public void createOrderAndChangesStatus() {
        Order order = OrderObjectMother.validAndWithoutItem();
        order.setStatus(OrderStatus.CANCELED.getCode());
        Assertions.assertEquals(2, order.getStatus());
    }

}
