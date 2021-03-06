package com.kroger.axon.commandmodel;

import java.util.UUID;

import com.kroger.axon.coreapi.exceptions.UnconfirmedOrderException;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import com.kroger.axon.coreapi.commandmodel.OrderAggregate;
import com.kroger.axon.coreapi.commands.ConfirmOrderCommand;
import com.kroger.axon.coreapi.commands.PlaceOrderCommand;
import com.kroger.axon.coreapi.commands.ShipOrderCommand;
import com.kroger.axon.coreapi.events.OrderConfirmedEvent;
import com.kroger.axon.coreapi.events.OrderPlacedEvent;
import com.kroger.axon.coreapi.events.OrderShippedEvent;

public class OrderAggregateUnitTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
               .when(new PlaceOrderCommand(orderId, product))
               .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @Test
    public void givenOrderPlacedEvent_whenConfirmOrderCommand_thenShouldPublishOrderConfirmedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ConfirmOrderCommand(orderId))
               .expectEvents(new OrderConfirmedEvent(orderId));
    }

    @Test
    public void givenOrderPlacedEvent_whenShipOrderCommand_thenShouldThrowUnconfirmedOrderException() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ShipOrderCommand(orderId))
               .expectException(UnconfirmedOrderException.class);
    }

    @Test
    public void givenOrderPlacedEventAndOrderConfirmedEvent_whenShipOrderCommand_thenShouldPublishOrderShippedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
               .when(new ShipOrderCommand(orderId))
               .expectEvents(new OrderShippedEvent(orderId));
    }
}