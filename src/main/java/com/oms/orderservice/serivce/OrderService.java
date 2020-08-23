package com.oms.orderservice.serivce;

import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.retrieveorder.RetrieveOrder;


public interface OrderService {

     String createOrder(CreateOrderRequest order);

     RetrieveOrder retrieveOrder(String orderNumber);
}
