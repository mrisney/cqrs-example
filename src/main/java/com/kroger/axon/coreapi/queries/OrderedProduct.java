package com.kroger.axon.coreapi.queries;

import lombok.Data;

@Data
public class OrderedProduct {

	private final String orderId;
	private final String product;
	private OrderStatus orderStatus;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderConfirmed() {
		this.orderStatus = OrderStatus.CONFIRMED;
	}

	public void setOrderShipped() {
		this.orderStatus = OrderStatus.SHIPPED;
	}
}
