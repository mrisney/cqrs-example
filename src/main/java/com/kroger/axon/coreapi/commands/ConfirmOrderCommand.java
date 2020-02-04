package com.kroger.axon.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class ConfirmOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;

	public ConfirmOrderCommand(String orderId) {
		this.orderId = orderId;
	}

}
