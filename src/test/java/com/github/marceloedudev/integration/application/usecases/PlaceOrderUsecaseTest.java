package com.github.marceloedudev.integration.application.usecases;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.application.dto.PlaceOrderInputOrderItems;
import com.github.marceloedudev.application.dto.PlaceOrderOutput;
import com.github.marceloedudev.application.usecases.PlaceOrderUsecase;
import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.infra.database.dao.ItemDAODatabase;
import com.github.marceloedudev.unit.domain.entity.Item.ItemObjectMother;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@QuarkusTest
public class PlaceOrderUsecaseTest {

    @Inject
    PlaceOrderUsecase placeOrderUsecase;

    @InjectMock
    ItemDAODatabase itemDAO;

    Long itemId = 10L;

    @BeforeEach
    public void setup() {
        Item item = ItemObjectMother.valid();
        Mockito.when(itemDAO.findById(itemId)).thenReturn(Optional.of(item));
    }

    @Test
    public void createOrder() {
        List<PlaceOrderInputOrderItems> orderItems = Arrays.asList(
                new PlaceOrderInputOrderItems(itemId, 2)
        );
        PlaceOrderInput order = new PlaceOrderInput(20L, "139.543.310-04", orderItems);
        PlaceOrderOutput output = placeOrderUsecase.execute(order);
        Assertions.assertEquals(true, output.getSuccess());
    }
}
