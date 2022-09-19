package com.github.marceloedudev.application.usecases;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.application.dto.PlaceOrderOutput;
import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.domain.usecases.Usecase;
import com.github.marceloedudev.infra.database.dao.ItemDAODatabase;
import com.github.marceloedudev.infra.database.repository.OrderRepositoryDatabase;
import com.github.marceloedudev.infra.metrics.PlaceOrderMetrics;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;
import com.github.marceloedudev.pkg.tracing.OpenTracing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class PlaceOrderUsecase implements Usecase<PlaceOrderInput, PlaceOrderOutput> {

    private final LoggerAdapter log = LoggerAdapter.getLogger(PlaceOrderUsecase.class);
    private ItemDAODatabase itemDAO;
    private OrderRepositoryDatabase orderRepositoryDatabase;
    private PlaceOrderMetrics placeOrderMetrics;
    private OpenTracing openTracing;

    @Inject
    public PlaceOrderUsecase(ItemDAODatabase itemDAO, OrderRepositoryDatabase orderRepositoryDatabase, PlaceOrderMetrics placeOrderMetrics, OpenTracing openTracing) {
        this.itemDAO = itemDAO;
        this.orderRepositoryDatabase = orderRepositoryDatabase;
        this.placeOrderMetrics = placeOrderMetrics;
        this.openTracing = openTracing;
    }

    @Override
    @Transactional
    public PlaceOrderOutput execute(PlaceOrderInput input) {
        openTracing.startSpanFromContext("PlaceOrderUsecase", "usecase");
        Order order = new Order(input.getUserId(), input.getCpf());
        if (order.validate(input.getOrderItems().size())) {
            throw new BadRequestException(order.getNotifications());
        }
        input.getOrderItems().stream().forEach(item -> {
            Optional<Item> itemFounded = itemDAO.findById(item.getIdItem());
            if (itemFounded.isEmpty()) {
                throw new NotFoundException("Item not found");
            }
            order.addItem(itemFounded.get(), item.getQuantity());
        });
        orderRepositoryDatabase.create(order);
        openTracing.logFields("success"); // order id
        openTracing.finish();
        log.info("Success place order order={}", order);
        placeOrderMetrics.addCountSuccess();
        return new PlaceOrderOutput("Place order successfully");
    }

}
