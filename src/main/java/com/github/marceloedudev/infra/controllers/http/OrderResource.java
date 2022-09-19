package com.github.marceloedudev.infra.controllers.http;

import com.github.marceloedudev.infra.dto.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface OrderResource {
    @POST
    @Operation(summary = "Place order")
    @APIResponses({
        @APIResponse(
            responseCode = "201",
            description = "Return message success",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(
                    implementation = PlaceOrderResponse.class,
                    type = SchemaType.OBJECT
                )
            )
        ),
        @APIResponse(
                responseCode = "400",
                description = "Validation error"
        ),
        @APIResponse(
                responseCode = "404",
                description = "Item not found"
        )
    })
    Response placeOrder(PlaceOrderRequest order);

    @PATCH
    @Path("{id}")
    @Operation(summary = "Cancel order")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Return message success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = CancelOrderResponse.class,
                                    type = SchemaType.OBJECT
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Validation error"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })
    Response cancelOrder(@PathParam("id") Long id);

    @GET
    @Path("{id}")
    @Operation(summary = "Get order by user")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Return message success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = GetOrderResponse.class,
                                    type = SchemaType.OBJECT
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })
    Response getOrder(@PathParam("id") Long id, @QueryParam("userId") Long userId);

    @GET
    @Operation(summary = "List orders by user")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Return message success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    implementation = GetOrdersResponse.class,
                                    type = SchemaType.OBJECT
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Validation error"
            ),
    })
    Response getOrders(@PathParam("userId") Long userId, @QueryParam("page") int page, @QueryParam("size") int size);
}
