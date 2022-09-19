Feature: Order Controller

  Scenario: Place order valid
    Given item with id 20 and price 300 for valid order
    When create order with valid item
    Then check if the order was created successfully with status 201
    And check if the order was created successfully with the property returns success

  Scenario: Cancel order valid
    Given valid order to cancel with valid item
    When cancel valid order
    Then check the status of the canceled order