package com.oms.orderservice.controller;

import com.oms.orderservice.exception.OrderServiceException;
import com.oms.orderservice.model.createorder.CreateOrder;
import com.oms.orderservice.model.createorder.CreateOrderItem;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.retrieveorder.RetrieveOrder;
import com.oms.orderservice.model.retrieveorder.RetrieveOrderItem;
import com.oms.orderservice.serivce.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class OrderServiceControllerTest {


    @InjectMocks
    OrderServiceController orderServiceController;

    @Mock
    OrderService orderService;

    @Test
    public void testCreateOrder(){

        when(orderService.createOrder(Mockito.any())).thenReturn("TESTORDER");
        ResponseEntity<CreateOrder> response = orderServiceController.createOrder(getCreateOrderRequest());
        Assert.assertNotNull(response);
        verify(orderService, times(1)).createOrder(getCreateOrderRequest());

    }

    @Test
    public void testRetrieveOrder(){

        when(orderService.retrieveOrder(Mockito.anyString())).thenReturn(getRetrieveOrder());
        ResponseEntity<RetrieveOrder> response = orderServiceController.retrieveOrder("TESTORDER");
        Assert.assertNotNull(response);
        verify(orderService, times(1)).retrieveOrder(Mockito.anyString());
    }

    @Test(expected = OrderServiceException.class)
    public void testCreateOrderFailed(){
        when(orderService.createOrder(null)).thenThrow(new OrderServiceException("exception occurred"));
        ResponseEntity<CreateOrder> response = orderServiceController.createOrder(null);
    }

    private RetrieveOrder getRetrieveOrder() {

        RetrieveOrder retrieveOrder = new RetrieveOrder();
        RetrieveOrderItem retrieveOrderItem = new RetrieveOrderItem();
        List<RetrieveOrderItem> retrieveOrderItemList = new ArrayList<>();
        retrieveOrderItem.setProductCode("pc001");
        retrieveOrderItem.setQuantity(2);
        retrieveOrderItem.setProductName("product xyz");
        retrieveOrder.setCustomerName("abcdef");
        retrieveOrder.setOrderDate(new Date());
        retrieveOrderItemList.add(retrieveOrderItem);
        retrieveOrder.setOrderItems(retrieveOrderItemList);

        return retrieveOrder;

    }


    private CreateOrderRequest getCreateOrderRequest() {

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        CreateOrderItem createOrderItem = new CreateOrderItem();
        createOrderItem.setProductCode("pc001");
        createOrderItem.setQuantity(5);
        createOrderRequest.setTotalAmount(25.80);
        List<CreateOrderItem> createOrderItemList = new ArrayList<>();
        createOrderItemList.add(createOrderItem);

        createOrderRequest.setCustomerName("abcdef");
        createOrderRequest.setShippingAddress("address1,city1");
        createOrderRequest.setOrderItems(createOrderItemList);
        return createOrderRequest;
    }
}
