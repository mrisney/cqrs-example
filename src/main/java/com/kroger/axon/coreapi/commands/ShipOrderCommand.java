package com.kroger.axon.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class ShipOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;

	public ShipOrderCommand(String orderId) {
		this.orderId = orderId;
	}
}
