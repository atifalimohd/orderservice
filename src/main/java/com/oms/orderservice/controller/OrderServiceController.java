package com.oms.orderservice.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oms.orderservice.model.createorder.CreateOrder;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.retrieveorder.RetrieveOrder;
import com.oms.orderservice.serivce.OrderService;
import com.oms.orderservice.util.OrderServiceConstants;

@RestController
public class OrderServiceController {

    @Autowired
    OrderService orderService;

    @PostMapping("/createorder")
    public ResponseEntity<CreateOrder> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest){

       CreateOrder createOrder = new CreateOrder();
       String orderNumber = orderService.createOrder(createOrderRequest);
       createOrder.setMessage(OrderServiceConstants.ORDER_CREATED_MESSAGE);
       createOrder.setOrderNumber(orderNumber);
       return new ResponseEntity<>(createOrder, HttpStatus.OK);
    }

    @GetMapping("/retrieveorder")
    public ResponseEntity<RetrieveOrder> retrieveOrder(@RequestParam("orderId") String orderId){

        RetrieveOrder retrieveOrder = orderService.retrieveOrder(orderId);
        return new ResponseEntity<>(retrieveOrder,HttpStatus.OK);

    }

}
