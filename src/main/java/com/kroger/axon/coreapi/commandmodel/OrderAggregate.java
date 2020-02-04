package com.kroger.axon.coreapi.commandmodel;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.kroger.axon.coreapi.commands.ConfirmOrderCommand;
import com.kroger.axon.coreapi.commands.PlaceOrderCommand;
import com.kroger.axon.coreapi.commands.ShipOrderCommand;
import com.kroger.axon.coreapi.events.OrderConfirmedEvent;
import com.kroger.axon.coreapi.events.OrderPlacedEvent;
import com.kroger.axon.coreapi.events.OrderShippedEvent;
import com.kroger.axon.coreapi.exceptions.UnconfirmedOrderException;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	private boolean orderConfirmed;

	@CommandHandler
	public OrderAggregate(PlaceOrderCommand command) {
		apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
	}

	@CommandHandler
	public void handle(ConfirmOrderCommand command) {
		apply(new OrderConfirmedEvent(orderId));
	}

	@CommandHandler
	public void handle(ShipOrderCommand command) {
		if (!orderConfirmed) {
			throw new UnconfirmedOrderException();
		}

		apply(new OrderShippedEvent(orderId));
	}

	@EventSourcingHandler
	public void on(OrderPlacedEvent event) {
		this.orderId = event.getOrderId();
		this.orderConfirmed = false;
	}

	@EventSourcingHandler
	public void on(OrderConfirmedEvent event) {
		this.orderConfirmed = true;
	}

	protected OrderAggregate() {
		// Required by Axon to build a default Aggregate prior to Event Sourcing
	}

}
