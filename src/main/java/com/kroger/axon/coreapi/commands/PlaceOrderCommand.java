package com.kroger.axon.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class PlaceOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;
	private final String product;

	public PlaceOrderCommand(String orderId, String product) {
		this.orderId = orderId;
		this.product = product;
	}

}
