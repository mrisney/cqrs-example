package com.kroger.axon.coreapi.events;

import lombok.Data;

@Data
public class OrderConfirmedEvent {

	private final String orderId;

	public OrderConfirmedEvent(String orderId) {
		this.orderId = orderId;
	}

}
