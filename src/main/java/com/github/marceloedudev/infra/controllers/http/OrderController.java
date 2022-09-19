package com.github.marceloedudev.infra.controllers.http;

import com.github.marceloedudev.application.commands.PlaceOrderCommand;
import com.github.marceloedudev.application.dto.*;
import com.github.marceloedudev.application.queries.GetOrderQuery;
import com.github.marceloedudev.application.queries.GetOrdersQuery;
import com.github.marceloedudev.application.usecases.CancelOrderUsecase;
import com.github.marceloedudev.domain.mappers.PlaceOrderMap;
import com.github.marceloedudev.infra.dto.*;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/checkout/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController implements OrderResource {

    private CancelOrderUsecase cancelOrderUsecase;
    private GetOrderQuery getOrderQuery;
    private GetOrdersQuery getOrdersQuery;
    private PlaceOrderCommand placeOrderCommand;

    @Inject
    public OrderController(CancelOrderUsecase cancelOrderUsecase, GetOrderQuery getOrderQuery, GetOrdersQuery getOrdersQuery, PlaceOrderCommand placeOrderCommand) {
        this.cancelOrderUsecase = cancelOrderUsecase;
        this.getOrderQuery = getOrderQuery;
        this.getOrdersQuery = getOrdersQuery;
        this.placeOrderCommand = placeOrderCommand;
    }

    @Override
    public Response placeOrder(PlaceOrderRequest request) {
        this.placeOrderCommand.execute(PlaceOrderMap.requestToInput(request));
        PlaceOrderResponse placeOrderOutput = new PlaceOrderResponse("Place order successfully");
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(placeOrderOutput)
                .build();
    }

    @Override
    public Response cancelOrder(Long id) {
        CancelOrderOutput cancelOrderUsecase = this.cancelOrderUsecase.execute(new CancelOrderInput(id));
        CancelOrderResponse cancelOrderOutput = new CancelOrderResponse(cancelOrderUsecase.getMessage());
        return Response.ok(cancelOrderOutput).build();
    }

    @Override
    public Response getOrder(Long id, Long userId) {
        GetOrderOutput getOrder = getOrderQuery.execute(new GetOrderInput(id, userId));
        GetOrderResponse getOrderResponse = new GetOrderResponse(getOrder.getOrder());
        return Response.ok(getOrderResponse).build();
    }

    @Override
    public Response getOrders(Long userId, int page, int size) {
        GetOrdersOutput orders = getOrdersQuery.execute(new GetOrdersInput(userId, page, size));
        GetOrdersResponse getOrdersResponse = new GetOrdersResponse(orders.getOrders());
        return Response.ok(getOrdersResponse).build();
    }

}
