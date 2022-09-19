package com.github.marceloedudev.integration.infra.controllers.http;

import com.github.marceloedudev.application.dto.PlaceOrderInput;
import com.github.marceloedudev.application.dto.PlaceOrderInputOrderItems;
import com.github.marceloedudev.application.usecases.PlaceOrderUsecase;
import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.infra.database.repository.ItemRepositoryDatabase;
import com.github.marceloedudev.unit.domain.entity.Item.ItemObjectMother;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CancelOrderSteps {

    @Inject
    PlaceOrderUsecase placeOrderUsecase;

    @Inject
    ItemRepositoryDatabase itemRepositoryDatabase;

    Long itemId = 10L;

    ValidatableResponse result;

    @Before()
    @Transactional
    public void setup() {
        Item item = ItemObjectMother.valid();
        itemId = itemRepositoryDatabase.create(item).getIdItem();
    }

    @Given("^valid order to cancel with valid item$")
    public void valid_order_to_cancel_with_valid_item() {
        List<PlaceOrderInputOrderItems> orderItems = Arrays.asList(
                new PlaceOrderInputOrderItems(itemId, 2)
        );
        PlaceOrderInput order = new PlaceOrderInput(20L, "139.543.310-04", orderItems);
        placeOrderUsecase.execute(order);
    }

    @When("^cancel valid order$")
    public void cancel_valid_order() {
        Long orderId = 1L;
        result = given()
                .contentType(ContentType.JSON)
        .when()
                .patch("/checkout/v1/" + orderId)
                .peek()
        .then();
    }

    @Then("^check the status of the canceled order$")
    public void check_the_status_of_the_canceled_order() throws Exception {
        result.statusCode(200);
    }

}
