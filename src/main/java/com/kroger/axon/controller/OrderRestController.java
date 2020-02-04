package com.kroger.axon.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.axon.coreapi.commands.ConfirmOrderCommand;
import com.kroger.axon.coreapi.commands.PlaceOrderCommand;
import com.kroger.axon.coreapi.commands.ShipOrderCommand;
import com.kroger.axon.coreapi.queries.FindAllOrderedProductsQuery;
import com.kroger.axon.coreapi.queries.OrderedProduct;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@RestController
@Api(tags = "Orders", description = "REST Methods for query and commands")
@Slf4j
public class OrderRestController {
	
	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryGateway queryGateway;
	
	@PostMapping("/ship-order")
	public void shipOrder() {
	    String orderId = UUID.randomUUID().toString();
	    commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
	    commandGateway.send(new ConfirmOrderCommand(orderId));
	    commandGateway.send(new ShipOrderCommand(orderId));
	}
	
	@GetMapping("/all-orders")
	public List<OrderedProduct> findAllOrderedProducts() {
	    return queryGateway.query(new FindAllOrderedProductsQuery(), 
	      ResponseTypes.multipleInstancesOf(OrderedProduct.class)).join();
	}
}
